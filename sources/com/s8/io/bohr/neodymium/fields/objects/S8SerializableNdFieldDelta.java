package com.s8.io.bohr.neodymium.fields.objects;

import com.s8.io.bohr.atom.S8Object;
import com.s8.io.bohr.atom.S8Serializable;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdField;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.type.BuildScope;
import com.s8.io.bytes.alpha.MemoryFootprint;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class S8SerializableNdFieldDelta extends NdFieldDelta {
	
	
	public final S8SerializableNdField field;
	
	public final S8Serializable value;

	
	
	public S8SerializableNdFieldDelta(S8SerializableNdField field, S8Serializable value) {
		super();
		this.field = field;
		this.value = value;
	}

	@Override
	public void consume(S8Object object, BuildScope scope) throws NdIOException {
		field.handler.set(object, value);
	}
	
	
	@Override
	public NdField getField() { 
		return field;
	}
	
	
	@Override
	public void computeFootprint(MemoryFootprint weight) {
		if(value!=null) {
			weight.reportInstance();
			weight.reportBytes(value.computeFootprint());	
		}
	}

}
