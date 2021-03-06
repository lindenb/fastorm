package com.github.lindenb.fastorm.model;

@SuppressWarnings("serial")
public class EModelException extends Exception {

	public EModelException() {
	}

	public EModelException(String arg0) {
		super(arg0);
	}

	public EModelException(Throwable arg0) {
		super(arg0);
	}

	public EModelException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EModelException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
