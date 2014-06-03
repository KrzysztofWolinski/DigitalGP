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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
}
