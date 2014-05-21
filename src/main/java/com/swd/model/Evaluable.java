package model;

import exceptions.NoValueAssignedException;

public interface Evaluable {
	Boolean getValue() throws NoValueAssignedException;
}
