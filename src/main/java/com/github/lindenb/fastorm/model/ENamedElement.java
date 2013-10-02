package com.github.lindenb.fastorm.model;


public interface ENamedElement
	extends EModelElement
	{
	public String getName();	
	public String getJavaName();
	public String getLabel();
	public String getLabels();
	public String getDescription();
	}
