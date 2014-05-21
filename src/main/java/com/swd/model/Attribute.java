package model;

import exceptions.NoValueAssignedException;

public class Attribute implements Evaluable {
	private String description;
	private Boolean value = null;
	
	public Attribute(String description) {
		this.description = description;
	}
	
	public Attribute(String description, boolean value) {
		this.description = description;
		this.value = value;
	}

	public Boolean getValue() throws NoValueAssignedException {
		if (value != null) {
			return new Boolean(value);
		}
		else {
			throw new NoValueAssignedException();
		}
			
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	public String getDescription() {
		return this.description;
	}
}
