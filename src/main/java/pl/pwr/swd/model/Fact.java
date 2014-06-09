package pl.pwr.swd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.pwr.swd.exceptions.NoValueAssignedException;

@Entity
@Table(name = "facts")
public class Fact {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Evaluable e;
	
	public Fact() {
		
	}
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
