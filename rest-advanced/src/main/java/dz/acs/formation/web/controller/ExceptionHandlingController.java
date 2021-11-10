package dz.acs.formation.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
@Getter
@Setter
@NoArgsConstructor
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	//AccessDeniedException

	// Total control - setup a model and return the view name yourself. Or
	// consider subclassing ExceptionHandlerExceptionResolver (see below).
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		StringBuffer requestURL = req.getRequestURL();
		log.info("Request: " + requestURL + " raised " + ex);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", requestURL);
		mav.setViewName("error");
		return mav;
	}

}
