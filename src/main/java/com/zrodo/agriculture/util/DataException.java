package com.zrodo.agriculture.util;

public class DataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6956666213410640672L;
	private Throwable root = null;
	public DataException()
	{
	}
	
	public DataException(String message)
	{
		super(message);
	}
	
	public DataException(String message, Throwable root)
	{
		super(message);
		this.root = root;
	}
}
