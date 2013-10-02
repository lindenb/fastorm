package com.github.lindenb.fastorm.model.impl;

import java.io.File;
import com.github.lindenb.fastorm.model.*;

public abstract class EClassifierImpl
	extends ENamedElementImpl
	implements EClassifier
	{
	private EPackage ePackage;
	
	protected EClassifierImpl()
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
