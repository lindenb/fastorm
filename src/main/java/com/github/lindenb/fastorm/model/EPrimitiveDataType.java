package com.github.lindenb.fastorm.model;


public interface EPrimitiveDataType extends EDataType
	{
	public abstract Class<?> getJavaClass();
	public boolean isOnlyPointer();
	public String getPrimitive();
	}
