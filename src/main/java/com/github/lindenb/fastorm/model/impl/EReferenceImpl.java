package com.github.lindenb.fastorm.model.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import com.github.lindenb.fastorm.model.*;

public class EReferenceImpl
	extends EStructuralFeatureImpl
	implements EReference
	{
	private String referenceType=null;
	private EClass _range=null;
	//private String opositeAttr;
	
	public EClass getEReferenceType() throws EModelException
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
			if(_range==null) throw new EModelException("Cannot get reference type for "+referenceType );
			}
		return _range;
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
	public final EClassifier getEType() {
		try {
			return getEReferenceType();
		} catch (EModelException e) {
			throw new RuntimeException(e);
			}
		}
	
	
	@Override
	void load(Element root) throws EModelException
		{
		super.load(root);
		
		Attr att=root.getAttributeNode("type");
		if(att==null) throw new EModelException("@type missing in "+getClass());
		this.referenceType=att.getValue();
		
		
		}
	
}
