package com.github.lindenb.fastorm.model;

public interface EDataType extends EClassifier {
	@Override
	public boolean isEEnum();
	public boolean isEPrimitive();
	}
