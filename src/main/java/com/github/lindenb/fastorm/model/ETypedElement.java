package com.github.lindenb.fastorm.model;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public abstract class ETypedElement extends ENamedElement {
private int lowerBound=0;
private int upperBound=1;
private boolean unique;
private boolean required;

protected  ETypedElement()
	{
	}

public int getLowerBound()
	{
	return lowerBound;
	}
public void setLowerBound(int lowerBound)
	{
	this.lowerBound = lowerBound;
	}
public int getUpperBound()
	{
	return upperBound;
	}
public void setUpperBound(int upperBound)
	{
	this.upperBound = upperBound;
	}
public boolean isUnique()
	{
	return unique;
	}
public void setUnique(boolean unique)
	{
	this.unique = unique;
	}
public boolean isRequired()
	{
	return required;
	}
public void setRequired(boolean required)
	{
	this.required = required;
	}

public abstract EClassifier getEType();

public final boolean isNeedsList()
	{
	return getUpperBound()==-1 || getUpperBound()>1;
	}

void load(Element root) throws EModelException
	{
	super.load(root);
	Attr att=root.getAttributeNode("lower-bound");
	if(att!=null)
		{
		this.lowerBound=Integer.parseInt(att.getValue());
		if(this.lowerBound<0) this.lowerBound=0;
		}
	att=root.getAttributeNode("upper-bound");
	if(att!=null)
		{
		if(att.getValue().equals("unbounded"))
			{
			this.upperBound=-1;
			}
		else
			{
			this.upperBound=Integer.parseInt(att.getValue());
			}
		if(this.upperBound<0) this.upperBound=-1;
		}
	
	}	
}
