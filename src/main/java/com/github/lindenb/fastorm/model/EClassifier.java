package com.github.lindenb.fastorm.model;

import java.io.File;

public abstract class EClassifier extends ENamedElement
	{
	private EPackage ePackage;
	
	protected EClassifier()
		{
		}
	
	public EPackage getEPackage() {
		return ePackage;
		}
	
	public void setEPackage(EPackage ePackage) {
		this.ePackage = ePackage;
		}
	
	public String getQName()
		{
		return getEPackage().getName()+"."+getJavaName();
		}
	
	public File getFile()
		{
		return new File(getEPackage().getOutputDirectory(),getName()+".java");
		}
	
	
	public abstract boolean isEClass();
	public abstract boolean isEEnum();
	}
