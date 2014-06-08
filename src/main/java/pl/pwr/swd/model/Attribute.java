package pl.pwr.swd.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.pwr.swd.exceptions.NoValueAssignedException;

@Entity
@Table(name="evaluables")
@DiscriminatorValue("Attribute")
public class Attribute extends Evaluable {

	@Column(name="description")
	private String description;
	
	@Column(name="is_input")
	private Boolean isInput = false;
	
	@Transient
	private Boolean value = null;
	
	public Boolean getIsInput() {
		return isInput;
	}

	public void setIsInput(Boolean isInput) {
		this.isInput = isInput;
	}

	public Attribute(String description) {
		this.description = description;
	}

	public Attribute() {
		this.description = null;
		this.value = null;
	}

	public Attribute(String description, boolean isInput) {
		this.description = description;
		this.isInput = isInput;
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
