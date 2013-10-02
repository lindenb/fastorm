package com.github.lindenb.fastorm.model;

public interface EAttribute
	extends EStructuralFeature
	{
	public void setId(boolean b);
	public boolean isId();
	public EDataType getEAttributeType();
	public void setEAttributeType(EDataType eAttributeType);
	}
