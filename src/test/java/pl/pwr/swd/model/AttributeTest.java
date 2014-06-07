package pl.pwr.swd.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.pwr.swd.exceptions.NoValueAssignedException;


public class AttributeTest {

	@Test(expected = NoValueAssignedException.class)
	public void shouldThrowExceptionIfTryingToGetValueOfNotInitialisedObject() throws NoValueAssignedException {
		Attribute a = new Attribute("test");
		a.getValue();
	}
	
	@Test
	public void shouldAssignProperValueToTheObject() throws NoValueAssignedException {
		Attribute a = new Attribute("test", true);
		assertTrue(a.getValue());
		
		Attribute b = new Attribute("test", false);
		assertFalse(b.getValue());
	}
	
}
