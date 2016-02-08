package com.blackiceinc.era.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String admin(Locale locale,  HttpServletRequest request, HttpServletResponse response, Model model){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
//		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//	 	response.addCookie(new Cookie("XSRF-TOKEN", token.getToken()));
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
}
