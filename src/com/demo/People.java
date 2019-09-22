package com.demo;

import com.configmodel.Person;

public class People extends Person {
	public String name;
	private int age;
	public long nianling;
	public String xingming;

	public People() {

	}

	public People(long nianling, String xingming) {

		this.nianling = nianling;
		this.xingming = xingming;
	}

	public void function() {
		System.out.println("public的无参方法");
	}

	private void function1() {
		System.out.println("private的无参方法");

	}

	public void function2(String name) {
		System.out.println("public " + name + nianling + xingming);
	}

	private void function3(String name) {
		System.out.println("private " + name);
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + ", nianling=" + nianling + ", xingming=" + xingming + "]";
	}

}
