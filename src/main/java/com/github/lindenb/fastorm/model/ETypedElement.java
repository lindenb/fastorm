package com.github.lindenb.fastorm.model;

import org.w3c.dom.Element;

public abstract class ETypedElement extends ENamedElement {
private int lowerBound=1;
private int upperBound=-1;
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


void load(Element root) throws EModelException
	{
	super.load(root);
	}	
}
