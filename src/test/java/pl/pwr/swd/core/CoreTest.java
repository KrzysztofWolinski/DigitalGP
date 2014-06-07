package pl.pwr.swd.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import pl.pwr.swd.exceptions.NoValueAssignedException;
import pl.pwr.swd.model.Attribute;
import pl.pwr.swd.model.Expression;
import pl.pwr.swd.model.Fact;
import pl.pwr.swd.model.Operation;

public class CoreTest {

	@Test
	public void shouldAnalyzeSimpleExample() throws NoValueAssignedException {
		
		/*
		 * Test data:
		 *  F1 = a1 OR a2	// e1
		 *  F2 = a1 AND a2	// e2
		 *  F3 = a1			// a1
		 *  
		 *  inputList = a1
		 *  outputList = a2
		 */
		
		ArrayList<Attribute> inputList = new ArrayList<Attribute>();
		Attribute a1 = new Attribute("input attribute", true);
		inputList.add(a1);
		
		ArrayList<Attribute> outputList = new ArrayList<Attribute>();
		Attribute a2 = new Attribute("output attribute");
		outputList.add(a2);
		
		Expression e1 = new Expression(a1, Operation.OR, a2);
		Expression e2 = new Expression(a1, Operation.AND, a2);
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(new Fact(e1));	// F1 = a1 OR a2
		facts.add(new Fact(e2));	// F2 = a1 AND a2
		facts.add(new Fact(a1));	// F3 = a1
		
		ArrayList<ArrayList<Attribute>> result = Core.analyseData(inputList, facts, outputList);
		
		assertTrue(facts.get(0).getValue());
		assertEquals(result.size(), 1);
	}
	
	@Test
	public void shouldAnalyzeSlightlyMoreComplicatedExample() throws NoValueAssignedException {
		
		/*
		 * Test data:
		 *  F1 = a1 AND a2 => a3							// e1
		 *  F2 = a3 OR a4									// e2
		 *  F3 = (a1 AND NOT(a4)) OR (NOT(a1) AND a4) 		// e3
		 *  F4 = a1											// a1
		 *  
		 *  inputList = a1, a2
		 *  outputList = a3, a4
		 */
		
		ArrayList<Attribute> inputList = new ArrayList<Attribute>();
		Attribute a1 = new Attribute("input attribute a1", true);
		Attribute a2 = new Attribute("input attribute a2", false);
		inputList.add(a1);
		inputList.add(a2);
		
		ArrayList<Attribute> outputList = new ArrayList<Attribute>();
		Attribute a3 = new Attribute("output attribute a3");
		Attribute a4 = new Attribute("output attribute a4");
		outputList.add(a3);
		outputList.add(a4);
		
		
		Expression e1_a = new Expression(a1, Operation.AND, a2);
		Expression e1 = new Expression(e1_a, Operation.IMPLICATION, a3);
		
		Expression e2 = new Expression(a3, Operation.OR, a4);
		
		Expression e3_a = new Expression(a1, Operation.AND, new Expression(a4, Operation.NOT, null));
		Expression e3_b = new Expression(a4, Operation.AND, new Expression(a1, Operation.NOT, null));
		Expression e3 = new Expression(e3_a, Operation.OR, e3_b);
		
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(new Fact(e1));	// F1 = a1 AND a2 => a3
		facts.add(new Fact(e2));	// F2 = a3 OR a4
		facts.add(new Fact(e3));	// F3 = (a1 AND NOT(a4)) OR (NOT(a1) AND a4)
		facts.add(new Fact(a1));	// F4 = a1
		
		ArrayList<ArrayList<Attribute>> result = Core.analyseData(inputList, facts, outputList);
		
		assertEquals(1, result.size());
	}
	
}
