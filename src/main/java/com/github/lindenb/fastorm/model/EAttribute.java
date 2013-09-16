package com.github.lindenb.fastorm.model;

public class EAttribute
	extends EStructuralFeature
	{
	private boolean iD;
	private EDataType eAttributeType;
	
	public void setId(boolean b)
		{
		this.iD = b;
		}
	
	public boolean isId()
		{
		return iD;
		}
	
	public EDataType getEAttributeType()
		{
		return eAttributeType;
		}
	
	public void setEAttributeType(EDataType eAttributeType)
		{
		this.eAttributeType = eAttributeType;
		}
	
	
	@Override
	public boolean isEAttribute()
		{
		return true;
		}
	
	@Override
	public boolean isEReference()
		{
		return false;
		}
	}
