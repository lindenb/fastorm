package com.github.lindenb.fastorm.model;

public interface EStructuralFeature
	extends ETypedElement
		{
		public boolean isEAttribute();
		public boolean isEReference();
		public EClass getEClass();
		public EPackage getEPackage();
		public EModel getEModel();
		}
