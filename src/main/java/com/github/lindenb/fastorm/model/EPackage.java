package com.github.lindenb.fastorm.model;

import java.io.File;
import java.util.List;


public interface EPackage extends ENamedElement
	{
	public String getQName();
	public EClassifier getEClassifierByQName(String qName);
	public File getOutputDirectory();
	public EModel getEModel();
	public List<EClassifier> getEClassifiers();
	public EClassifier getEClassifierByName(String name);
	public List<EClass> getEClasses();
	public List<EEnum> getEEnums();
	public EClass getEClassByName(String name);
	public EEnum getEENumByName(String name);
	}
