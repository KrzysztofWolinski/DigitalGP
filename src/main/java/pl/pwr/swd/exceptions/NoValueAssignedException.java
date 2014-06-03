package pl.pwr.swd.exceptions;

public class NoValueAssignedException extends Exception {
	
	public NoValueAssignedException() {
		super();
	}
	
	@Override
	public String toString() {
		return "NoValueAssignedException!";
	}
}