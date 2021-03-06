package com.s8.io.bohr.neon.methods;

import java.io.IOException;

import com.s8.io.bohr.neon.core.NeObjectTypeHandler;
import com.s8.io.bytes.alpha.ByteInflow;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public abstract class NeMethod {


	public NeObjectTypeHandler prototype;
	/**
	 * 
	 */
	public int code;
	
	public int ordinal;
	
	
	public final String name;
	
	
	/**
	 * 
	 * @param name
	 */
	public NeMethod(NeObjectTypeHandler prototype, String name) {
		super();
		this.prototype = prototype;
		this.name = name;
	}
	
	
	public abstract long getSignature();
	
	
	public NeFunc createFunc() {
		return new NeFunc();
	}
	

	public abstract NeRunnable buildRunnable(ByteInflow inflow) throws IOException;
	
}
