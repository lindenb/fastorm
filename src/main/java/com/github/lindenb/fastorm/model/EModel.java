package com.github.lindenb.fastorm.model;

import java.io.File;
import java.util.List;

public interface EModel
	extends ENamedElement
	{
	public EClass getMainEClass();
	public List<EClass> getAllEClasses();
	public EPackage getEPackageByName(String qName);
	public String getPackage();
	public EClassifier getEClassifierByQName(String qName);
	public EClass getEClassByQName(String qName);
	public EEnum getEEnumByQName(String qName);
	public List<EPackage> getEPackages();
	public File getOutputDirectory();
	public void setOutputDirectory(File outputDirectory);
	public  List<EPrimitiveDataType> getAllEPrimitives();
	public EPrimitiveDataType getEPrimitiveByName(String name);
	public EPrimitiveDataType getEPrimitiveByQName(String name);
	}
