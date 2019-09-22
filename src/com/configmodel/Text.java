package com.configmodel;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;

/*
 * 如何在不修改代码的情况下，灵活使用Person,Student,Worder的方法。
 * 可以提前在配置文件中写好全类名和方法，然后利用反射动态的获取到该对象方法运行，此时只需要修改配置文件就可以了
 * !!!!!!!配置文件中properties的键值对不用以；封号结尾，否则；封号也算配置文字
 */
public class Text {
	public static void main(String[] args) throws Exception {
		// io流读取配置文件
		FileReader reader = new FileReader("config.properties");
		// 创建集合对象
		Properties pro = new Properties();
		// 调用该集合对象，传入io流
		pro.load(reader);
		// 关闭io流
		reader.close();
		// 通过配置文件的键值对获取值
		String className = pro.getProperty("ClassName");
		String FunctionName = pro.getProperty("functionName");
		// 获取配置文件的类
		Class c = Class.forName(className);
		Object obj = c.newInstance();
		Method method = c.getMethod(FunctionName);
		method.invoke(obj);
	}

}
