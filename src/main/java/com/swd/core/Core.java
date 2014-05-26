package com.swd.core;

import java.util.ArrayList;

import com.swd.dao.Dao;
import com.swd.exceptions.NoValueAssignedException;
import com.swd.model.Attribute;
import com.swd.model.Fact;


public class Core {
	private ArrayList<Attribute> inputList;
	private ArrayList<Attribute> outputList;
	private ArrayList<Fact> facts;
	
	public void prepareData(ArrayList<Attribute> input) {
		inputList = input;
		outputList = Dao.getOutputAttributes();
		facts = Dao.getFacts();
	}
	
	public ArrayList<Attribute> analyseData() {
		// TODO Implement an algorithm
		
		ArrayList<Attribute> returnList = new ArrayList<Attribute>();
		
		// For each possible permutation
		for (int i = 0; i < Math.pow(outputList.size(), 2); i++) {
			
			// Set values of output for testing facts
			for (int j = 0; j < outputList.size(); j++) {
				boolean value = i % Math.pow(j, 2) == 0 ? false : true;
				outputList.get(j).setValue(value);
			}
			
			// Check if Facts are true or not
			for (Fact f : facts) {
				try {
					if (f.getValue() == true) {
						// TODO add current combination to the resultList
					}
				}
				catch (NoValueAssignedException e) {
					continue;
				}
			}
		}
		
		// for every possible combination of output
		
		// check if fact is true
		
		// if false, don't takie it into account
		
		// if true, add the output combination to return list
		
		// if an exception is thrown - skip
		
		return returnList;
	}
}
