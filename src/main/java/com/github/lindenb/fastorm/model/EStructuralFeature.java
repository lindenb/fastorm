package com.github.lindenb.fastorm.model;

public abstract class EStructuralFeature
	extends ETypedElement
		{
		protected EStructuralFeature()
			{
			
			}
		public abstract boolean isEAttribute();
		public abstract boolean isEReference();
		}
