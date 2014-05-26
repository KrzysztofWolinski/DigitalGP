package com.swd.model;

import com.swd.exceptions.NoValueAssignedException;

public interface Evaluable {
	Boolean getValue() throws NoValueAssignedException;
}
