package com.github.lindenb.fastorm.model;

public abstract class ETypedElement extends ENamedElement {
private int lowerBound=1;
private int upperBound=-1;
private boolean unique;
private boolean required;
private EClassifier eType;

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

public EClassifier 	getEType()
	{
	return this.eType;
	}

public void seteType(EClassifier eType)
	{
	this.eType = eType;
	}

}
