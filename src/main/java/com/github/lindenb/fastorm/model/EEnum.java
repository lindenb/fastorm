package com.github.lindenb.fastorm.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EEnum extends EDataType
	{
	private List<EEnumLiteral> items=new ArrayList<EEnumLiteral>();
	
	public List<EEnumLiteral> getELiterals()
		{
		return this.items;
		}
	
	public EEnumLiteral getELiteralByName(String name)
		{
		for(EEnumLiteral E:getELiterals())
			{
			if(E.getName().equals(name)) return E;
			}
		return null;
		}
	
	@Override
	public boolean isEPrimitive()
		{
		return false;
		}
	
	@Override
	public final boolean isEClass() {
		return false;
		}

	@Override
	public final boolean isEEnum() {
		return true;
		}
	
	void load(Element root) throws EModelException
		{
		Attr att=root.getAttributeNode("name");
		if(att==null) throw new EModelException("@name missing in model");
		String s=att.getValue();
		if(!s.matches("[a-zA-Z]\\w*")) throw new  EModelException("bad package name "+s);
		s=s.substring(0,1).toUpperCase()+(s.length()>1?s.substring(2):"");
		setName(s);
		for(Node c1=root.getFirstChild();c1!=null;c1=c1.getNextSibling())
			{
			if(c1.getNodeType()!=Node.ELEMENT_NODE) continue;
			Element e1=(Element)c1;
			if(e1.getLocalName().equals("item"))
				{
				EEnumLiteral item=new  EEnumLiteral();
				item.setEEnum(this);
				this.items.add(item);
				item.load(e1);
				}
			}
		
		}
	}
