package com.github.lindenb.fastorm.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EModel
	extends ENamedElement
	{
	private  File outputDirectory;
	private String packaging=null;
	private List<EPackage> packages=new ArrayList<EPackage>();
	private EClass mainEclass=null;
	
	public EClass getMainEClass()
		{
		return mainEclass;
		}
	
	public EPackage getEPackageByName(String qName)
		{
		for(EPackage p:getPackages())
			{
			if(p.getName().equals(qName)) return p;
			}
		return null;
		}
	
	public String getPackage()
		{
		return packaging==null?"com.github.lindenb."+getName().toLowerCase():packaging;
		}
	
	public EClassifier getEClassifierByQName(String qName)
		{
		for(EPackage p:getPackages())
			{
			EClassifier ec=p.getEClassifierByQName(qName);
			if(ec!=null) return ec;
			}
		return null;
		}
	
	public EClass getEClassByQName(String qName)
		{
		EClassifier ec= getEClassifierByQName(qName);
		return ec!=null && ec.isEClass()?(EClass)ec:null;
		}
	
	public EEnum getEEnumByQName(String qName)
		{
		EClassifier ec= getEClassifierByQName(qName);
		return ec!=null && ec.isEEnum()?(EEnum)ec:null;
		}

	
	public List<EPackage> getPackages()
		{
		return packages;
		}
	
	public File getOutputDirectory()
		{
		return outputDirectory;
		}
	
	public void setOutputDirectory(File outputDirectory)
		{
		this.outputDirectory = outputDirectory;
		}
	
	
	public  List<EPrimitiveDataType> getAllEPrimitives()
		{
		return Arrays.asList(EPrimitiveDataType.getAllEPrimitives());
		}
	
	public EPrimitiveDataType getEPrimitiveByName(String name)
		{
		for(EPrimitiveDataType p:getAllEPrimitives())
			{
			if(name.equalsIgnoreCase(p.getQName())) return p;
			if(name.equalsIgnoreCase(p.getPrimitive())) return p;
			if(name.equalsIgnoreCase(p.getJavaClass().getSimpleName())) return p;
			}
		return null;
		}
	
	
	public EPrimitiveDataType getEPrimitiveByQName(String name)
		{
		for(EPrimitiveDataType p:getAllEPrimitives())
			{
			if(name.equals(p.getQName())) return p;
			}
		return null;
		}
	
	private void load(File xmlFile) throws EModelException
	{
		try
		{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setCoalescing(true);
		factory.setExpandEntityReferences(true);
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder=factory.newDocumentBuilder();
		Element root=builder.parse(xmlFile).getDocumentElement();
		if(!root.getNodeName().equals("model"))
			{
			throw new EModelException("root is not model in "+xmlFile);
			}
		this.load(root);
		}
		catch(Exception err)
		{
			throw new EModelException(err);
		}
	}
	
	private void load(Element root) throws EModelException
		{
		Attr att=root.getAttributeNode("name");
		if(att==null) throw new EModelException("@name missing in model");
		String s=att.getValue();
		if(!s.matches("[a-zA-Z]\\w*")) throw new  EModelException("bad model name "+s);
		setName(s);
		for(Node c1=root.getFirstChild();c1!=null;c1=c1.getNextSibling())
			{
			if(c1.getNodeType()!=Node.ELEMENT_NODE) continue;
			Element e1=(Element)c1;
			if(e1.getNodeName().equals("package"))
				{
				EPackage p=new EPackage();
				p.setEModel(this);
				this.packages.add(p);
				p.load(e1);
				}
			}
		att=root.getAttributeNode("main-class");
		if(att==null) throw new EModelException("@main-class missing in model");
		this.mainEclass=getEClassByQName(att.getValue());
		if(this.mainEclass==null) throw new EModelException("cannot find main class "+att.getValue()+"  in model");
		
		}
	
	public class Tool
		{
		
		}
	
	public class MultiWriter
	extends Writer
		{
		private PrintWriter pw=null;
		public File filename=null;
		
		public File getFile()
			{
			return this.filename;
			}
		
		private void _open(String fName,boolean ignoreIfExists)throws IOException
			{
			close();
			
			this.filename=new File(EModel.this.getOutputDirectory(),fName);
			if(ignoreIfExists && this.filename.exists())
					{
					return;
					}
			LOG.info("opening "+this.filename);
			if(this.filename.getParentFile()!=null)
				{
				this.filename.getParentFile().mkdirs();
				}
			this.pw=new PrintWriter(this.filename);
			}
		
		
		public void open(String fName)throws IOException
			{
			_open(fName,false);
			}
			
		public void openIfMissing(String fName)throws IOException
			{
			_open(fName,true);
			}
		
		public void openClass(String className)throws IOException
			{
			_open(
				EModel.this.getName().toLowerCase()+
				"/src/main/java/"+
				className.replace('.', File.separatorChar)+".java",
				false);
			}
		
		
		@Override
		public void close() throws IOException
			{
			flush();
			if(pw!=null)
				{
				LOG.info("closing "+this.filename);
				pw.close();
				pw=null;	
				}
			filename=null;
			}
	
		@Override
		public void flush() throws IOException
			{
			if(pw!=null)
				{
				pw.flush();
				}
			else if(this.filename==null)
				{
				System.out.flush();
				}
			}
	
		@Override
		public void write(char[] cbuf, int off, int len) throws IOException
			{
			if(pw!=null)
				{
				pw.write(cbuf, off, len);		
				}
			else if(this.filename==null)
				{
				System.out.print(new String(cbuf, off, len));
				}
			}
		@Override
		public String toString()
			{
			return filename==null?"null":filename.toString();
			}
		}

	
	
	private int run(String args[])throws EModelException,IOException
		{
		LOG.setLevel(Level.ALL);
		File velocityTemplate=null;
		int optind=0;
		while(optind<args.length)
			{
			if(args[optind].equals("-h"))
				{
				return 0;
				}
			else if(args[optind].equals("-T") && optind+1<args.length)
				{
				velocityTemplate=new File(args[++optind]);
				LOG.info("velocityTemplate "+velocityTemplate);
				}
			else if(args[optind].equals("-o") && optind+1<args.length)
				{
				outputDirectory=new File(args[++optind]);
				LOG.info("output directory "+outputDirectory);
				}
			else if(args[optind].equals("--"))
				{
				optind++;
				break;
				}
			else if(args[optind].startsWith("-"))
				{
				System.err.println("Unnown option: "+args[optind]);
				return -1;
				}
			else
				{
				break;
				}
			++optind;
			}
		
		if(velocityTemplate==null)
			{
			System.err.println("velocity template missing.");
			return -1;
			}
		
		if(optind+1!=args.length)
			{
			System.err.println("illegal number of arguments");
			return -1;
			}
		
		this.load(new File(args[optind]));
		
		VelocityContext context=new VelocityContext();

		MultiWriter out=new MultiWriter();
		context.put("out",out);
		context.put("model",this);
		context.put("tool",new Tool());
		context.put("now",new java.sql.Timestamp(System.currentTimeMillis()));
		
		LOG.info("Reading VELOCITY template from file "+velocityTemplate);
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("resource.loader", "file");
		ve.setProperty(
				"file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.FileResourceLoader"
				);
		if(velocityTemplate.getParent()!=null)
			{
			ve.setProperty(
					"file.resource.loader.path",
					velocityTemplate.getParent()
					);
			}
		ve.init();
		Template template = ve.getTemplate(velocityTemplate.getName());
		LOG.info("merging");
		template.merge(context, out);
		out.close();
		
		return 0;
		}
	
	public static void main(String[] args) throws EModelException,IOException
		{
		int err=new EModel().run(args);
		System.exit(err);
		}
	
	}
