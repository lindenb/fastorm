package com.github.lindenb.fastorm.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EPackage extends ENamedElement
	{
	private List<EClassifier> classifiers=new ArrayList<EClassifier>();
	private EModel eModel;
	
	
	public final String getQName()
		{
		return this.getName();
		}
	
	public EClassifier getEClassifierByQName(String qName)
		{
		for(EClassifier ec:getEClassifiers())
			{
			if(ec.getQName().equals(qName)) return ec;
			}
		return null;
		}

	
	
	public File getOutputDirectory()
		{
		return new File(getEModel().getOutputDirectory(),
			"src/main/java/"+getName().replace('.', '/')
			);
		}
	
	public void setEModel(EModel eModel)
		{
		this.eModel = eModel;
		}
	
	public EModel getEModel()
		{
		return eModel;
		}
	
	public List<EClassifier> getEClassifiers()
		{
		return this.classifiers;
		}
	
	public EClassifier getEClassifierByName(String name)
		{
		for(EClassifier ec:getEClassifiers())
			{
			if(ec.getName().equals(name)) return ec;
			}
		return null;
		}
	
	public List<EClass> getEClasses()
		{
		List<EClass> L=new ArrayList<EClass>();
		for(EClassifier E:getEClassifiers())
			{
			if(E.isEClass()) L.add((EClass)E);
			}
		return L;
		}
	
	
	

	public List<EEnum> getEEnums()
		{
		List<EEnum> L=new ArrayList<EEnum>();
		for(EClassifier E:getEClassifiers())
			{
			if(E.isEEnum()) L.add((EEnum)E);
			}
		return L;
		}
	
	public EClass getEClassByName(String name)
		{
		EClassifier c=getEClassifierByName(name);
		return c!=null && c.isEClass()?(EClass)c:null;
		}

	
	public EEnum getEENumByName(String name)
		{
		EClassifier c=getEClassifierByName(name);
		return c!=null && c.isEEnum()?(EEnum)c:null;
		}

	void load(Element root) throws EModelException
		{
		Attr att=root.getAttributeNode("name");
		if(att==null) throw new EModelException("@name missing in model");
		String s=att.getValue();
		if(!s.matches("[a-zA-Z]\\w*(\\.[a-zA-Z]\\w*)*")) throw new  EModelException("bad package name "+s);
		setName(s);
		for(Node c1=root.getFirstChild();c1!=null;c1=c1.getNextSibling())
			{
			if(c1.getNodeType()!=Node.ELEMENT_NODE) continue;
			Element e1=(Element)c1;
			if(e1.getNodeName().equals("class"))
				{
				EClass p=new EClass();
				p.setEPackage(this);
				p.load(e1);
				this.classifiers.add(p);
				}
			else if(e1.getNodeName().equals("enum"))
				{
				EEnum p=new EEnum();
				p.setEPackage(this);
				p.load(e1);
				this.classifiers.add(p);
				}
			}
		}
	
	}
