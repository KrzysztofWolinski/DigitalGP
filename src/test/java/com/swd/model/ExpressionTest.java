package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.NoValueAssignedException;

public class ExpressionTest {
	
	@Test
	public void shouldCreateProperAndExpression() throws NoValueAssignedException {
		Attribute a = new Attribute("test1_a", true);
		Attribute b = new Attribute("test1_b", true);
		Expression e = new Expression(a, Operation.AND, b);
		assertTrue(e.getValue());
		
		a.setValue(false);
		b.setValue(true);
		assertFalse(e.getValue());
		
		a.setValue(true);
		b.setValue(false);
		assertFalse(e.getValue());
		
		a.setValue(false);
		b.setValue(false);
		assertFalse(e.getValue());
	}
	
	@Test
	public void shouldCreateProperOrExpression() throws NoValueAssignedException {
		Attribute a = new Attribute("test1_a", true);
		Attribute b = new Attribute("test1_b", true);
		Expression e = new Expression(a, Operation.OR, b);
		assertTrue(e.getValue());
		
		a.setValue(false);
		b.setValue(true);
		assertTrue(e.getValue());
		
		a.setValue(true);
		b.setValue(false);
		assertTrue(e.getValue());
		
		a.setValue(false);
		b.setValue(false);
		assertFalse(e.getValue());
	}
	
	@Test
	public void shouldCreateProperIdentityExpression() throws NoValueAssignedException {
		Attribute a = new Attribute("test1_a", true);
		Attribute b = new Attribute("test1_b", true);
		Expression e = new Expression(a, Operation.IDENTITY, b);
		assertTrue(e.getValue());
		
		a.setValue(false);
		b.setValue(true);
		assertFalse(e.getValue());
		
		a.setValue(true);
		b.setValue(false);
		assertFalse(e.getValue());
		
		a.setValue(false);
		b.setValue(false);
		assertTrue(e.getValue());
	}
	
	@Test
	public void shouldCreateProperImplicationExpression() throws NoValueAssignedException {
		Attribute a = new Attribute("test1_a", true);
		Attribute b = new Attribute("test1_b", true);
		Expression e = new Expression(a, Operation.IMPLICATION, b);
		assertTrue(e.getValue());
		
		a.setValue(false);
		b.setValue(true);
		assertTrue(e.getValue());
		
		a.setValue(true);
		b.setValue(false);
		assertFalse(e.getValue());
		
		a.setValue(false);
		b.setValue(false);
		assertTrue(e.getValue());
	}
	
	@Test(expected = NoValueAssignedException.class)
	public void shouldThrowExceptionIfOneofTheValuesWasntInitialised() throws NoValueAssignedException {
		Attribute a = new Attribute("test1_a");
		Attribute b = new Attribute("test1_b", true);
		Expression e = new Expression(a, Operation.IMPLICATION, b);
	}
}
