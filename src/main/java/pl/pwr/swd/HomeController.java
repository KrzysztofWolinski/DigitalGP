package pl.pwr.swd;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
	@RequestMapping(value = "/", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/analyse", method = RequestMethod.GET)
	public String analyseRequestGet(Locale locale, Model model) {
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<?> input_attributes = session.createQuery(
			    "from evaluables as ev where ev.is_input = ?")
			    .setBoolean(0, true)
			    .list();
		model.addAttribute("input_attributes", input_attributes);
					
		return "input";
	}
	
	@Transactional
	@RequestMapping(value = "/analyse", method = RequestMethod.POST)
	public String analyseRequestPost(Locale locale, Model model) {
		
		// Read attributes from the db
		
		// Receive input from the user
		
		// Call Core method
		
		// Return results
				
		return "analyse";
	}
	
}
