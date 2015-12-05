package com.eintern.business;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller
//@RequestMapping("")
public class DirectAccessRedirectSecurityController {

	//@RequestMapping(method = RequestMethod.GET)
/*	public void redirectPage(HttpSession session) {
		System.out.println("--------------------DirectAccessRedirect--------------------------");
		if(session.getAttribute("user")==null){
			return "login";
		}
		else{
			return " ";
		}
	}*/
	
	//@Pointcut("execution(* com.eintern.business.DirectAccessRedirectSecurityController.*(..))")
	public void redirectPage() {
	 System.out.println("--------------------DirectAccessRedirect--------------------------");
	 //return "hi";
	}
}
