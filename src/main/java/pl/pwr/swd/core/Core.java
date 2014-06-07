package pl.pwr.swd.core;

import java.util.ArrayList;

import pl.pwr.swd.exceptions.NoValueAssignedException;
import pl.pwr.swd.model.Attribute;
import pl.pwr.swd.model.Fact;


public class Core {
	
	public static ArrayList<ArrayList<Attribute>> analyseData(ArrayList<Attribute> inputList, ArrayList<Fact> facts, ArrayList<Attribute> outputList) throws NoValueAssignedException {
		ArrayList<ArrayList<Attribute>> returnList = new ArrayList<ArrayList<Attribute>>();
		
		// For each possible permutation
		for (int i = 0; i < Math.pow(2, outputList.size()); i++) {
			
			// Set values of output for testing facts
			for (int j = 0; j < outputList.size(); j++) {
				int mask = (int)Math.pow(2, j);
				boolean value = (i & mask) == 0 ? false : true;
				outputList.get(j).setValue(value);
			}
			
			// Check if Facts are true or not
			boolean isPermutationValid = true;
			for (Fact f : facts) {
				try {
					if (f.getValue() == false) {
						isPermutationValid = false;
						break;
					}
				}
				catch (NoValueAssignedException e) {
					continue;
				}
			}
			
			if (isPermutationValid) {
				returnList.add((ArrayList<Attribute>) outputList.clone());
			}
		}
		
		return returnList;
	}
}
