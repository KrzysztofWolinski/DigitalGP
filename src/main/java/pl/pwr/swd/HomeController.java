package pl.pwr.swd;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pwr.swd.core.Core;
import pl.pwr.swd.exceptions.NoValueAssignedException;
import pl.pwr.swd.model.Attribute;
import pl.pwr.swd.model.Fact;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Transactional
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		LinkedList<String> list = new LinkedList<String>();
		for(int i = 0; i < 10; i++) list.add("Numer" + i);
		model.addAttribute("list", list);
		
		return "home";
	}
	
	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String analyseRequestGet(Locale locale, Model model) {
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Attribute> input_attributes = (List<Attribute>) session.createQuery(
				    "from Evaluable where is_input = ?")
				    .setBoolean(0, true)
				    .list();
			
			model.addAttribute("input_attributes", input_attributes);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println(e);
		}
				
		return "input";
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value = "/analyse", method = RequestMethod.POST)
	public String analyseRequestPost(@RequestParam Map<String, String> params, Model model) {
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			List<Attribute> input_attributes = (List<Attribute>) session.createQuery("from Evaluable where is_input = ?")
				    .setBoolean(0, true)
				    .list();
			List<Attribute> output_attributes = (List<Attribute>) session.createQuery("from Evaluable where is_input = ?")
				    .setBoolean(0, false)
				    .list();
			List<Fact> facts = (List<Fact>) session.createQuery("from Fact")
				    .list();
			
			tx.commit();
			for(Attribute a : input_attributes) {
				if(params.containsKey(a.getId().toString())) {
					a.setValue(true);
				} else {
					a.setValue(false);
				}
			}
			ArrayList<ArrayList<Attribute>> results = Core.analyseData(input_attributes, facts, output_attributes);
			model.addAttribute("result_list", results);
		} catch (HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println(e);
		} catch (NoValueAssignedException e) {
			e.printStackTrace();
		}
				
		return "results";
	}
	
}
