package com.github.lindenb.fastorm.model;

public interface EReference extends EStructuralFeature
	{
	public EClass getEReferenceType() throws EModelException;	
	public EClassifier getEType();
	}
