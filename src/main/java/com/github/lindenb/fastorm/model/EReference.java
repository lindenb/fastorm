package com.github.lindenb.fastorm.model;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class EReference extends EStructuralFeature
	{
	private String referenceType=null;
	private EClass _range=null;
	
	public EClass getEReferenceType()
		{
		if(_range==null)
			{
			if(referenceType.contains("."))//qualified Name
				{
				_range=getEModel().getEClassByQName(referenceType);
				}
			else
				{
				_range= getEPackage().getEClassByName(referenceType);
				}
			}
		return _range;
		}
	
	public EReference getEOpposite()
		{
		return null;
		}
	
	@Override
	public boolean isEAttribute()
		{
		return false;
		}
	
	@Override
	public boolean isEReference()
		{
		return true;
		}
	
	
	@Override
	void load(Element root) throws EModelException
		{
		super.load(root);
		
		if(referenceType==null) throw new EModelException("missing reference type");
		}
	
}
