package com.github.lindenb.fastorm.model;

import java.util.ArrayList;
import java.util.List;

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
	}
