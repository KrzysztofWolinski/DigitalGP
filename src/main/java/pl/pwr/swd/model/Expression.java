package pl.pwr.swd.model;

import pl.pwr.swd.exceptions.NoValueAssignedException;


public class Expression extends Evaluable {
	private Evaluable a;
	private Evaluable b;
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
