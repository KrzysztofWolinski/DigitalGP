package pl.pwr.swd;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
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

import pl.pwr.swd.model.Attribute;

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

		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Attribute a = new Attribute();
			a.setDescription("This is the first description.");
			a.setValue(true);
			// Put into db
			session.save(a);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) tx.rollback();
			System.out.println(e);
			System.out.println("Dupa :(");
		}
		
		model.addAttribute("serverTime", formattedDate );
		LinkedList<String> list = new LinkedList<String>();
		for(int i = 0; i < 10; i++) list.add("Numer" + i);
		model.addAttribute("list", list);
		
		return "home";
	}
	
}
