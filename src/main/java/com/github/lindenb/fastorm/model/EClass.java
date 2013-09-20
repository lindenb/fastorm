package com.github.lindenb.fastorm.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class EClass extends EClassifier
	{
	private List<EStructuralFeature> structuralFeatures=new ArrayList<EStructuralFeature>();
	
	public List<EStructuralFeature> getEStructuralFeatures()
		{
		return structuralFeatures;
		}
	
	public EStructuralFeature geEStructuralFeatureByName(String name)
		{
		for(EStructuralFeature E:getEStructuralFeatures())
			{
			if(E.getName().equals(name)) return E;
			}
		return null;
		}
	
	public List<EAttribute> getEAttributes()
		{
		 List<EAttribute> L=new ArrayList<EAttribute>();
		for(EStructuralFeature E:getEStructuralFeatures())
			{
			if(E.isEAttribute()) L.add((EAttribute)E); 
			}
		return L;
		}
	
	public List<EReference> getEReferences()
		{
		 List<EReference> L=new ArrayList<EReference>();
		for(EStructuralFeature E:getEStructuralFeatures())
			{
			if(E.isEReference()) L.add((EReference)E); 
			}
		return L;
		}
	
	
	public EModel getEModel()
		{
		return getEPackage().getEModel();
		}
	
	
	public final boolean isMainEClass()
		{
		return getEModel().getMainEClass()==this;
		}
	
	@Override
	public final boolean isEClass() {
		return true;
	}

	@Override
	public final boolean isEEnum() {
		return false;
	}
	
	public  EAttribute 	getEIDAttribute()
		{
		for(EAttribute a:getEAttributes())
			{
			if(a.isId()) return a;
			}
		LOG.finest("getEIDAttribute return null for "+getQName());
		return null;
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
			if(e1.getNodeName().equals("reference"))
				{
				EReference a=new EReference();
				a.setEClass(this);
				this.structuralFeatures.add(a);
				a.load(e1);
				}
			else if(e1.getNodeName().equals("attribute"))
				{
				EAttribute a=new EAttribute();
				a.setEClass(this);
				this.structuralFeatures.add(a);
				a.load(e1);
				}
			}
		}
	
	}
