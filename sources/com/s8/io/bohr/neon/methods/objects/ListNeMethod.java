package com.s8.io.bohr.neon.methods.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.bohr.BOHR_Types;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObject;
import com.s8.io.bohr.neon.core.NeObjectTypeHandler;
import com.s8.io.bohr.neon.methods.NeFunc;
import com.s8.io.bohr.neon.methods.NeMethod;
import com.s8.io.bohr.neon.methods.NeRunnable;
import com.s8.io.bytes.alpha.ByteInflow;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class ListNeMethod<T extends NeObject> extends NeMethod {


	public interface Lambda<T extends NeObject> {
		
		public void operate(List<T> arg);
		
	}
	

	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.S8OBJECT;

	public @Override long getSignature() { return SIGNATURE; }

	
	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public ListNeMethod(NeObjectTypeHandler prototype, String name) {
		super(prototype, name);
	}



	@Override
	public NeRunnable buildRunnable(ByteInflow inflow) throws IOException {
		if(inflow.getUInt8() != BOHR_Types.ARRAY) {	throw new IOException("Must be an array"); }
		switch(inflow.getUInt8()) {
		case BOHR_Types.S8OBJECT : new S8ObjectNeRunnable<T>();
		default : throw new IOException("Unsupported type");
		}
	}
	
	
	private static class S8ObjectNeRunnable<T extends NeObject> implements NeRunnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			
			int length = (int) inflow.getUInt7x();
			if(length >= 0) {
				List<T> list =  new ArrayList<T>();
				for(int i=0; i<length; i++) {
					String index = inflow.getStringUTF8();
					list.add(index != null ? (T) branch.getObject(index) : null);
				}
				((Lambda<T>) (func.lambda)).operate(list);	
			}
			else {
				((Lambda<T>) (func.lambda)).operate(null);	
			}
			
		}
	}
	
}
