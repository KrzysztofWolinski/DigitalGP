package com.swd.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.swd.exceptions.NoValueAssignedException;


public class FactTest {
	
	@Test
	public void shouldEvaluateProperValueOfTheFactWithAndEvaluable() throws NoValueAssignedException {
		Attribute a = new Attribute("test");
		a.setValue(true);
		Attribute b = new Attribute("test");
		b.setValue(true);
		Evaluable e = new Expression(a, Operation.AND, b);
		
		Fact f = new Fact(e);
		assertTrue(f.getValue());
	}
	
	@Test
	public void shouldEvaluateProperValueOfTheFactWithOrEvaluable() throws NoValueAssignedException {
		Attribute a = new Attribute("test");
		a.setValue(true);
		Attribute b = new Attribute("test");
		b.setValue(true);
		Evaluable e = new Expression(a, Operation.OR, b);
		
		Fact f = new Fact(e);
		assertTrue(f.getValue());
	}
	
	@Test
	public void shouldEvaluateProperValueOfTheFactWithThreeVariables() throws NoValueAssignedException {
		
		// false AND true => true
		Attribute a = new Attribute("test");
		a.setValue(false);
		Attribute b = new Attribute("test");
		b.setValue(true);
		Attribute c = new Attribute("test");
		c.setValue(true);
		
		Evaluable e = new Expression(new Expression(a, Operation.AND, b), Operation.IMPLICATION, c);
		
		Fact f = new Fact(e);
		assertTrue(f.getValue());
	}
	
}
