package com.github.lindenb.fastorm.model;

import java.util.List;



public interface EClass extends EClassifier
	{
	public List<EStructuralFeature> getEStructuralFeatures();
	public EStructuralFeature geEStructuralFeatureByName(String name);
	public List<EAttribute> getEAttributes();
	public EAttribute getEAttributeByName(String name);
	public List<EReference> getEReferences();
	public EModel getEModel();
	public boolean isMainEClass();	
	public EAttribute 	getEIDAttribute();
	}
