package com.github.lindenb.fastorm.model;

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
	
	
	
	}
