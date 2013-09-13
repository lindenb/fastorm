package com.github.lindenb.fastorm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EPackage extends ENamedElement
	{
	private Map<String,EClassifier> name2classifier=new LinkedHashMap<String, EClassifier>();
	private EModel eModel;
	
	
	public void setEModel(EModel eModel)
		{
		this.eModel = eModel;
		}
	
	public EModel getEModel()
		{
		return eModel;
		}
	
	public List<EClassifier> getEClassifiers()
		{
		return new ArrayList<EClassifier>(this.name2classifier.values());
		}
	
	public EClassifier getEClassifierByName(String name)
		{
		return this.name2classifier.get(name);
		}
	
	public List<EClass> getEClasses()
		{
		List<EClass> L=new ArrayList<EClass>();
		for(EClassifier E:getEClassifiers())
			{
			if(E.isEClass()) L.add((EClass)E);
			}
		return L;
		}
	
	
	

	public List<EENum> getEEnums()
		{
		List<EENum> L=new ArrayList<EENum>();
		for(EClassifier E:getEClassifiers())
			{
			if(E.isEEnum()) L.add((EENum)E);
			}
		return L;
		}
	
	public EClass getEClassByName(String name)
		{
		EClassifier c=getEClassifierByName(name);
		return c!=null && c.isEClass()?(EClass)c:null;
		}

	
	public EENum getEENumByName(String name)
		{
		EClassifier c=getEClassifierByName(name);
		return c!=null && c.isEEnum()?(EENum)c:null;
		}

	
	
	}
