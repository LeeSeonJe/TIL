package com.sj.collection.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sj.collection.model.vo.Person;

public class MapController {
	Person p = new Person();

	public void start() {
		Map<Integer, String> m = new HashMap<Integer, String>();
		m.put(1, "LCJ");
		m.put(2, "KMO");
		m.put(3, "LSA");
		m.put(4, "LSJ");

		Set<Entry<Integer, String>> s = m.entrySet();
		Iterator<Entry<Integer, String>> s2 = s.iterator();
		
		Entry<Integer, String> e = s2.next();
		System.out.println(e);
	}
	
	public void test() {
		Map<Integer, Person> m = new HashMap<Integer, Person>();
		m.put(1, new Person(1,"LCJ"));
		
		System.out.println(m.values());
		
		Set<Entry<Integer, Person>> s = m.entrySet();
		Iterator<Entry<Integer, Person>> s1 = s.iterator();
		
		
	}
}
