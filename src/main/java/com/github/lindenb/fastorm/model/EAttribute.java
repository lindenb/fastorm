package com.github.lindenb.fastorm.model;


import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class EAttribute
	extends EStructuralFeature
	{
	private boolean iD;
	private EDataType eAttributeType;
	
	public void setId(boolean b)
		{
		this.iD = b;
		}
	
	public boolean isId()
		{
		return iD;
		}
	
	@Override
	public final EClassifier getEType() {
		return getEAttributeType();
		}
	
	public EDataType getEAttributeType()
		{
		return eAttributeType;
		}
	
	public void setEAttributeType(EDataType eAttributeType)
		{
		this.eAttributeType = eAttributeType;
		}
	
	
	@Override
	public final boolean isEAttribute()
		{
		return true;
		}
	
	@Override
	public final boolean isEReference()
		{
		return false;
		}
	
	@Override
	void load(Element root) throws EModelException
		{
		super.load(root);
		Attr att=root.getAttributeNode("type");
		if(att==null) throw new EModelException("@type missing in "+getClass());
		String value=att.getValue();
		if(value.contains("."))//qualified name
			{
			this.eAttributeType=getEModel().getEEnumByQName(value);
			if(this.eAttributeType==null)
				{
				this.eAttributeType=getEModel().getEPrimitiveByQName(value);
				if(this.eAttributeType==null) throw new EModelException("Cannot get @type="+value);
				}
			}
		else
			{
			this.eAttributeType=getEPackage().getEENumByName(value);
			if(this.eAttributeType==null)
				{
				this.eAttributeType=getEModel().getEPrimitiveByName(value);
				if(this.eAttributeType==null) throw new EModelException("Cannot get @type="+value);
				}
			}
		
		}

	}
