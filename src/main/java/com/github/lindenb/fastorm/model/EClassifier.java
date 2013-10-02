package com.github.lindenb.fastorm.model;

import java.io.File;

public interface EClassifier extends ENamedElement
	{
	public EPackage getEPackage();
	public String getQName();	
	public File getFile();
	public boolean isEClass();
	public boolean isEEnum();
	}
