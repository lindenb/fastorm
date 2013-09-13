package com.github.lindenb.fastorm.model;

public abstract class EClassifier extends ENamedElement
	{
	private EPackage ePackage;
	
	public EPackage getEPackage() {
		return ePackage;
	}
	
	public void setEPackage(EPackage ePackage) {
		this.ePackage = ePackage;
	}
	
	
	public abstract boolean isEClass();
	public abstract boolean isEEnum();
	}
