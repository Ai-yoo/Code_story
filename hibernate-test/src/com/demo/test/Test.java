package com.demo.test;

import com.demo.domain.Users1;
import com.demo.util.Session;

public class Test {
	public static void main(String[] args) {
		Session session=new Session();
		Users1 u=new Users1();
		u.setName("du");
		u.setPassword("12345");
		session.save(u);
	}
}
