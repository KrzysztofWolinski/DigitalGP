package core;

import java.util.ArrayList;

import model.Attribute;
import model.Fact;
import dao.AttributeDao;
import dao.FactDao;

public class Core {
	private ArrayList<Attribute> inputList;
	private ArrayList<Attribute> outputList;
	private ArrayList<Fact> facts;
	
	public void prepareData(ArrayList<Attribute> input) {
		FactDao factDao;
		AttributeDao attributeDao;
		
		// TODO loading data
	}
	
	public ArrayList<Attribute> analyseData() {
		// TODO Implement an algorithm
		
		return outputList;
	}
}
