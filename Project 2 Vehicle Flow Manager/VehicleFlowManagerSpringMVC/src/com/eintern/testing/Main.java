package com.eintern.testing;

import javax.inject.Inject;

public class Main {
	@Inject
	static TesterInterface tester;
	
	public static void main(String[] args) {
/*	ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Tester x = (Tester) ctx.getBean("tester");*/
		
		tester.aopTest();
	}

}
