package dz.acs.formation.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.java.Log;
/**
 * AccessController 
 * @author ataibi
 *
 */
@Controller
@Log
public class AccessController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage() {
		log.info("index page");
		return "welcome";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		log.info("login page");
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		log.info("logout page");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
