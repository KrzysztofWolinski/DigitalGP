package pl.pwr.swd.core;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.pwr.swd.HibernateUtils;
import pl.pwr.swd.model.Attribute;
import pl.pwr.swd.model.Expression;
import pl.pwr.swd.model.Fact;
import pl.pwr.swd.model.Operation;

public class TestDataInit {

	private static void initData() {
		Attribute goraczka = new Attribute("goraczka", false);
		Attribute kaszel = new Attribute("kaszel", true);
		Attribute bol_glowy = new Attribute("bol glowy", false);
		Attribute bole_miesni = new Attribute("bole miesni", false);
		Attribute katar = new Attribute("katar", true);
		
		Attribute przeziebienie = new Attribute("przeziebienie");
		Attribute grypa = new Attribute("grypa");
		Attribute ok = new Attribute("ok");
		Attribute migrena = new Attribute("migrena");
		
		ArrayList<Attribute> inputList = new ArrayList<Attribute>();
		inputList.add(goraczka);
		inputList.add(kaszel);
		inputList.add(bol_glowy);
		inputList.add(bole_miesni);
		inputList.add(katar);
		
		ArrayList<Attribute> outputList = new ArrayList<Attribute>();
		outputList.add(przeziebienie);
		outputList.add(grypa);
		outputList.add(ok);
		outputList.add(migrena);
		
		
		Expression e1_1 = new Expression(kaszel, Operation.AND, katar);
		Expression e1 = new Expression(e1_1, Operation.IDENTITY, przeziebienie);
		
		Expression e2_a = new Expression(goraczka, Operation.AND, bol_glowy);
		Expression e2_b = new Expression(e2_a, Operation.AND, bole_miesni);
		Expression e2 = new Expression(e2_b, Operation.IDENTITY, grypa);
		
		Expression e3_a = new Expression(new Expression(goraczka, Operation.NOT, null), Operation.AND, new Expression(bol_glowy, Operation.NOT, null));
		Expression e3_b = new Expression(e3_a, Operation.AND, new Expression(bole_miesni, Operation.NOT, null));
		Expression e3_c = new Expression(e3_b, Operation.AND, new Expression(przeziebienie, Operation.NOT, null));
		Expression e3 = new Expression(e3_c, Operation.IDENTITY, ok);
		
		Expression e4_a = new Expression(bol_glowy, Operation.AND, new Expression(grypa, Operation.NOT, null));
		Expression e4 = new Expression(e4_a, Operation.IDENTITY, migrena);

		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(new Fact(e1));
		facts.add(new Fact(e2));
		facts.add(new Fact(e3));
		facts.add(new Fact(e4));
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			for (Fact f : facts) {
				session.save(f);
			}
			
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		initData();
	}
	
}
