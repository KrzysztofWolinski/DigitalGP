package pl.pwr.swd.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.pwr.swd.exceptions.NoValueAssignedException;

@Entity
@Table(name="evaluables")
@DiscriminatorValue("Attribute")
public class Attribute extends Evaluable {

	@Column(name="description")
	private String description;
	@Column(name="value")
	private Boolean value = null;
	
	public Attribute(String description) {
		this.description = description;
	}

	public Attribute() {
		this.description = null;
		this.value = null;
	}

	public Attribute(String description, boolean value) {
		this.description = description;
		this.value = value;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getValue() throws NoValueAssignedException {
		if (value != null) {
			return new Boolean(value);
		}
		else {
			throw new NoValueAssignedException();
		}
			
	}
	public void setValue(Boolean value) {
		this.value = value;
	}
	
	public String toString() {
		return this.description + ": " + this.value;
	}
}
