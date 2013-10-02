package com.github.lindenb.fastorm.model;


public interface ETypedElement extends ENamedElement {
public int getLowerBound();
public int getUpperBound();
public boolean isUnique();
public boolean isRequired();
public EClassifier getEType();
public boolean isNeedsList();
}
