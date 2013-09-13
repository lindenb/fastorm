package com.github.lindenb.fastorm.model;

import java.util.ArrayList;
import java.util.List;


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
	
	
	@Override
	public final boolean isEClass() {
		return true;
	}

	@Override
	public final boolean isEEnum() {
		return false;
	}
	
	}
