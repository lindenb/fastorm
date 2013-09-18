package com.github.lindenb.fastorm.model;

public abstract class EDataType extends EClassifier {
	@Override
	public abstract boolean isEEnum();
	public abstract boolean isEPrimitive();
	}
