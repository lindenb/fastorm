package com.github.lindenb.fastorm.model.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.github.lindenb.fastorm.model.*;

public abstract class EPrimitiveDataTypeImpl
	extends EDataTypeImpl
	implements EPrimitiveDataType
	{
	public abstract Class<?> getJavaClass();
	
	
	@Override
	public final String getName()
		{
		return this.getQName();
		}
	
	public static final EPrimitiveDataTypeImpl E_STRING=new EPrimitiveDataTypeImpl()
		{
		@Override
		public final boolean isOnlyPointer() { return true;}
		@Override
		public Class<?> getJavaClass() { return String.class;};
		};
		
	public static final EPrimitiveDataTypeImpl E_CHAR=new EPrimitiveDataTypeImpl()
		{
		@Override
		public String getPrimitive()
			{
			return "char";
			}
		@Override
		public Class<?> getJavaClass() { return Character.class;};
		};
	public static final EPrimitiveDataTypeImpl E_BOOLEAN=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Boolean.class;};
		};

	public static final EPrimitiveDataTypeImpl E_BYTE=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Byte.class;};
		};
		
	public static final EPrimitiveDataTypeImpl E_SHORT=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Short.class;};
		};	
		
	public static final EPrimitiveDataTypeImpl E_INT=new EPrimitiveDataTypeImpl()
		{
		@Override
		public String getPrimitive()
			{
			return "int";
			}
		@Override
		public Class<?> getJavaClass() { return Integer.class;};
		};	
		
	public static final EPrimitiveDataTypeImpl E_LONG=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Long.class;};
		};	
	
	public static final EPrimitiveDataTypeImpl E_BIGINTEGER=new EPrimitiveDataTypeImpl()
		{
		@Override
		public final boolean isOnlyPointer() { return true;}
		@Override
		public Class<?> getJavaClass() { return BigInteger.class;};
		};		

	public static final EPrimitiveDataTypeImpl E_FLOAT=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Float.class;};
		};
			
	public static final EPrimitiveDataTypeImpl E_DOUBLE=new EPrimitiveDataTypeImpl()
		{
		@Override
		public Class<?> getJavaClass() { return Double.class;};
		};
		
	public static final EPrimitiveDataTypeImpl E_BIGDECIMAL=new EPrimitiveDataTypeImpl()
		{
		@Override
		public final boolean isOnlyPointer() { return true;}
		
		@Override
		public Class<?> getJavaClass() { return BigDecimal.class;};
		};
	
	public 	static EPrimitiveDataType[] getAllEPrimitives()
		{
		return new EPrimitiveDataType[]{
			E_STRING,E_CHAR,	
			E_BOOLEAN,E_BYTE,
			E_SHORT,E_INT,E_LONG,E_BIGINTEGER,
			E_FLOAT,E_DOUBLE,E_BIGDECIMAL
			};
		}
	
	public static EPrimitiveDataType getEPrimitiveByName(String name)
		{
		for(EPrimitiveDataType p:getAllEPrimitives())
			{
			if(name.equalsIgnoreCase(p.getQName())) return p;
			if(name.equalsIgnoreCase(p.getPrimitive())) return p;
			if(name.equalsIgnoreCase(p.getJavaClass().getSimpleName())) return p;
			}
		return null;
		}
	
	
	public boolean isOnlyPointer()
		{
		return false;
		}
	
	@Override
	public final boolean isEPrimitive()
		{
		return true;
		}
	@Override
	public final boolean isEEnum()
		{
		return false;
		}
	@Override
	public boolean isEClass()
		{
		return false;
		}
	
	@Override
	public final String getQName()
		{
		return getJavaClass().getName();
		}
	
	public String getPrimitive()
		{
		if(isOnlyPointer()) return getQName();
		return getJavaClass().getSimpleName().toLowerCase();
		}
	
	}
