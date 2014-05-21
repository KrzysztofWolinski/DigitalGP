package model;

import exceptions.NoValueAssignedException;

public class Fact {
	private Evaluable e;
	
	public Fact(Evaluable e) {
		this.e = e;
	}
	
	public boolean getValue() {
		try {
			return e.getValue();
		}
		catch (NoValueAssignedException e) {
			return false;
		}
	}
}
