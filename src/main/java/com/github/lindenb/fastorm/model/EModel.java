package com.github.lindenb.fastorm.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EModel
	extends ENamedElement
	{
	private  File outputDirectory;
	private List<EPackage> packages=new ArrayList<EPackage>();
	
	
	public EPackage getEPackageByName(String qName)
		{
		for(EPackage p:getPackages())
			{
			if(p.getName().equals(qName)) return p;
			}
		return null;
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
		}
	
	}
