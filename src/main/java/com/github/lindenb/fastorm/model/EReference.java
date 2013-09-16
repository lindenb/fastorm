package com.github.lindenb.fastorm.model;

public class EReference extends EStructuralFeature {
	public EClass getEReferenceType()
		{
		return null;
		}
	
	public EReference getEOpposite()
		{
		return null;
		}
	
	@Override
	public boolean isEAttribute()
		{
		return false;
		}
	
	@Override
	public boolean isEReference()
		{
		return true;
		}
}
