package com.github.lindenb.fastorm.model;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class ENamedElement
	extends EModelElement
	{
	private String name="";
	private String label=null;
	private String labels=null;
	private String description=null;
	
	
	@Override
	public int hashCode() {
		return name.hashCode();
		}
	
	public String getName()
		{
		return name;
		}
	
	public String getJavaName()
		{
		String s=getName();
		return s.substring(0, 1).toUpperCase()+(s.length()<2?"":s.substring(1));
		}
	
	public void setName(String name)
		{
		this.name = name;
		}
	@Override
	public String toString()
		{
		return getName();
		}

	public String getLabel() {
		return label;
	}

	/** get a label for this object */
	public void setLabel(String label) {
		this.label = label;
	}

	/** get plural form of label */
	public String getLabels() {
		return labels==null?getLabel():labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	/** get a larger description for this object */
	public String getDescription() {
		return description==null?getLabel():description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	void load(Element root) throws EModelException
		{
		Node prev=root.getPreviousSibling();
		while(prev!=null && prev.getNodeType()==Node.COMMENT_NODE)
			{
			if(this.description==null) this.description="";
			this.description=((Comment) prev).getData()+this.description;
			prev=prev.getPreviousSibling();
			}
			
		Attr att=root.getAttributeNode("label");
		if(att!=null)
			{
			this.label=att.getValue();
			this.labels=att.getValue();
			}
		att=root.getAttributeNode("labels");
		if(att!=null)
			{
			this.labels=att.getValue();
			}
		att=root.getAttributeNode("description");
		if(att!=null)
			{
			this.description=att.getValue();
			}
		for(Node c1=root.getFirstChild();
				c1!=null;
				c1=c1.getNextSibling())
			{
			if(c1.getNodeType()!=Node.ELEMENT_NODE) continue;
			Element e1=(Element)c1;
			if(e1.getNodeName().equals("label"))
				{
				this.label=e1.getTextContent();
				}
			else if(e1.getNodeName().equals("labels"))
				{
				this.labels=e1.getTextContent();
				}
			else if(e1.getNodeName().equals("description"))
				{
				this.description=e1.getTextContent();
				}
			}
		}	
	
	}
