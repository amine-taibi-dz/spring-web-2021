package dz.acs.formation.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;


/**
 * AccessController 
 * @author ataibi
 *
 */
@Controller
@RequestMapping("/api/wss/msg-handler")
@Log4j2
public class DemWebsocketController {
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String wssMessagePage() {
		log.info("wss-message page");
		return "wss-message";
	}
	
	
}
