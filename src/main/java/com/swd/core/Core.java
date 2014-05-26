package com.swd.core;

import java.util.ArrayList;

import com.swd.dao.AttributeDao;
import com.swd.dao.FactDao;
import com.swd.model.Attribute;
import com.swd.model.Fact;


public class Core {
	private ArrayList<Attribute> inputList;
	private ArrayList<Attribute> outputList;
	private ArrayList<Fact> facts;
	
	public void prepareData(ArrayList<Attribute> input) {
		FactDao factDao = new FactDao();
		AttributeDao attributeDao = new AttributeDao();
		
		inputList = input;
		outputList = attributeDao.getOutputAttributes();
		facts = factDao.getFacts();
	}
	
	public ArrayList<Attribute> analyseData() {
		// TODO Implement an algorithm
		
		return outputList;
	}
}
