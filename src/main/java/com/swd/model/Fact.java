package com.swd.model;

import com.swd.exceptions.NoValueAssignedException;

public class Fact {
	private Evaluable e;
	
	public Fact(Evaluable e) {
		this.e = e;
	}
	
	public boolean getValue() throws NoValueAssignedException {
		try {
			return e.getValue();
		}
		catch (NoValueAssignedException e) {
			return false;
		}
	}
}
