package com.github.lindenb.fastorm.model;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public abstract class EStructuralFeature
	extends ETypedElement
		{
		private EClass eClass;
		protected EStructuralFeature()
			{
			
			}
		public abstract boolean isEAttribute();
		public abstract boolean isEReference();
		
		public void setEClass(EClass eClass)
			{
			this.eClass = eClass;
			}
		
		public EClass getEClass()
			{
			return eClass;
			}
		
		public EPackage getEPackage()
			{
			return getEClass().getEPackage();
			}
		
		public EModel getEModel()
			{
			return getEClass().getEModel();
			}
		
		void load(Element root) throws EModelException
			{
			Attr att=root.getAttributeNode("name");
			if(att==null) throw new EModelException("@name missing in reference");
			String s=att.getValue();
			if(!s.matches("[a-zA-Z]\\w*")) throw new  EModelException("bad "+getClass()+" name "+s);
			this.setName(s);
			
			}
		
		}
