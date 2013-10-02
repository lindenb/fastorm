package com.github.lindenb.fastorm.model.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import com.github.lindenb.fastorm.model.*;

public class EEnumLiteralImpl
	extends ENamedElementImpl
	implements EEnumLiteral
	{
	private EEnum eEnum;
	
	public EEnum getEEnum()
		{
		return eEnum;
		}
	public void setEEnum(EEnum eEnum)
		{
		this.eEnum = eEnum;
		}
	
	
	void load(Element root) throws EModelException
		{
		Attr att=root.getAttributeNode("name");
		if(att==null) throw new EModelException("@name missing in model");
		String s=att.getValue();
		if(!s.matches("[a-zA-Z]\\w*")) throw new  EModelException("bad package name "+s);
		setName(s);
		
		}

	
	}
