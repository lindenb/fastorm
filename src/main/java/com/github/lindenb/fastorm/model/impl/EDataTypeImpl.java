package com.github.lindenb.fastorm.model.impl;

import com.github.lindenb.fastorm.model.EDataType;


public abstract class EDataTypeImpl
	extends EClassifierImpl
	implements EDataType
	{
	@Override
	public abstract boolean isEEnum();
	public abstract boolean isEPrimitive();
	}
