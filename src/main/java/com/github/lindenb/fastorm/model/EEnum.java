package com.github.lindenb.fastorm.model;

import java.util.List;


public interface EEnum extends EDataType
	{
	public List<EEnumLiteral> getELiterals();
	public EEnumLiteral getELiteralByName(String name);
	}
