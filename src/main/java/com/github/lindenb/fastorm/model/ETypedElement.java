package com.github.lindenb.fastorm.model;

public abstract class ETypedElement extends ENamedElement {
private int lowerBound=1;
private int upperBound=-1;
private boolean unique;
private boolean required;
}
