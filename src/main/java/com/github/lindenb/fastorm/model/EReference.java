package com.github.lindenb.fastorm.model;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class EReference extends EStructuralFeature
	{
	private String referenceType=null;
	private EClass _range=null;
	
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
