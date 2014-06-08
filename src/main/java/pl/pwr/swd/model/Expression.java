package pl.pwr.swd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.pwr.swd.exceptions.NoValueAssignedException;

@Entity
@Table(name="evaluables")
@DiscriminatorValue("Expression")
public class Expression extends Evaluable {
	@OneToOne(cascade = CascadeType.ALL)
	private Evaluable a;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Evaluable b;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "operation_type")
	private Operation operation;
	
	public Expression(Evaluable a, Operation operation,  Evaluable b) throws NoValueAssignedException {
		this.a = a;
		this.b = b;
		this.operation = operation;
	}
	
	@Override
	public Boolean getValue() throws NoValueAssignedException {
		if (this.operation == Operation.AND) {
			return a.getValue() && (b.getValue());
		}
		else if (this.operation == Operation.OR) {
			return a.getValue() || b.getValue();
		}
		else if (this.operation == Operation.IDENTITY) {
			return a.getValue().equals(b.getValue());
		}
		else if (this.operation == Operation.IMPLICATION) {
			if ((a.getValue().equals(true)) && (b.getValue().equals(false))) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (this.operation == Operation.NOT) {
			return this.a.getValue() ? false : true;
		}
		else {
			throw new NoValueAssignedException();
		}
	}

}
