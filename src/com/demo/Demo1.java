package com.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * 通过反射获取的class文件对象的构造方法，并运行构造方法
 */
public class Demo1 {
	/**
	 * 获取一个类的class文件对象的三种方式 。类加载加载的每个对象都是==的（）
	 */
	public static void function() throws Exception {
		// 1.对象获取
		People p = new People();
		// 调用People的父类方法getclass（）
		Class c = p.getClass();

		// 2.类名获取
		Class c1 = People.class;
		// 3.Class类的静态方法获取 forName（包名.类名）
		Class c2 = Class.forName("com.demo.People");

	}

	/*
	 * 获得空参构造方法
	 */
	public static void function01() throws Exception {
		// 1.获取people类的文件对象
		Class c = Class.forName("com.demo.People");
		// 2.只能获取类中public修饰的所有构造方法
		Constructor[] constructors = c.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
		// 获取空参构造方法
		Constructor con = c.getConstructor();// 这里只能获得public修饰的构造方法，其他的修饰符都不行，default也不行
		System.out.println(con);
	}

	/*
	 * 获得有参构造方法
	 */
	public static void youcanfunction() throws Exception {
		// 获取People文件对象
		Class<?> class1 = Class.forName("com.demo.People");
		// 获取people有参构造方法,参数要传相应类型的类文件对象
		Constructor<?> constructor = class1.getConstructor(String.class, int.class);
		System.out.println(constructor);
		People instance = (People) constructor.newInstance("xiaodeng", 100);
		System.out.println(instance);

	}

	/*
	 * 直接运行构造方法
	 */
	public static void function03() throws Exception {
		Class c = Class.forName("com.demo.People");
		// 直接运行构造方法
		Object o = c.newInstance();// 但是虽然这里没有规定必须是public，但是想要运行构造方法，你必须有权限
		System.out.println(o);
	}

	/*
	 * 反射获取私有的构造方法运行 不推荐,破坏了程序的封装性,安全性 暴力反射
	 */
	public static void huoqusiyoudefunction() throws Exception {
		Class<?> forName = Class.forName("com.demo.People");
		// *获取所有的构造方法，包括私有的
		Constructor<?>[] declaredConstructors = forName.getDeclaredConstructors();
		for (Constructor<?> constructor : declaredConstructors) {
			System.out.println(constructor);
		}
		// 获取指定参数列表的构造方法,包括私有的private
		Constructor<?> declaredConstructor = forName.getDeclaredConstructor(int.class, String.class);
		System.out.println(declaredConstructor);
	}

	/*
	 * 反射获取public 成员变量的值，并修改值
	 */
	public static void function05() throws Exception {
		Class c = Class.forName("com.demo.People");
		// 获取成员变量 class文件中的所有!!!!!!!public 公共的成员变量
		Field[] fields = c.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		// 获取指定的成员变量
		Field field2 = c.getField("name");
		System.out.println(field2);
		Object object = c.newInstance();
		field2.set(object, "xiaodeng");
		System.out.println(object);

	}

	/*
	 * 反射获取成员变量的值，并修改值
	 */
	public static void function005() throws Exception {
		Class c = Class.forName("com.demo.People");
		// 获取成员变量 class文件中的所有的成员变量,包括私有的
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		// 获取指定的成员变量
		Field field2 = c.getDeclaredField("name");
		System.out.println(field2);
		Object object = c.newInstance();
		field2.set(object, "xiaodeng");// 给object这个对象赋值
		System.out.println(field2.get(object));// 得到object这个对象该成员name变量的值
		System.out.println(object);

	}

	/*
	 * 反射获取成员方法
	 */
	public static void function06() throws Exception {
		Class c = Class.forName("com.demo.People");
		// 快速获取对象
		Object o = c.newInstance();
		// 获取本类与父类中所有public 修饰的方法
		Method[] methods = c.getMethods();
		for (Method string : methods) {
			System.out.println(string);
		}
		// 获取指定的public修饰的方法运行
		Method method = c.getMethod("action");
		System.out.println(method);

	}

	/*
	 * 获取本类中所有的方法(包含私有的)
	 */
	public static void function006() throws Exception {
		Class c = Class.forName("com.demo.People");
		// 快速获取对象
		Object o = c.newInstance();
		// 获取本类与父类中所有的方法
		Method[] methods = c.getDeclaredMethods();
		for (Method string : methods) {
			System.out.println(string);
		}
		// 获取指定的方法
		Method method = c.getDeclaredMethod("function1", null);// 参数传入方法名和方法参数
		System.out.println(method);

	}

	/*
	 * 执行找到的方法
	 */
	public static void function07() throws Exception {
		Class<?> class1 = Class.forName("com.demo.People");
		Constructor<?> constructor = class1.getConstructor(long.class, String.class);
		Object object = constructor.newInstance(12, "xiao he shang");
		Method method = class1.getMethod("function2", String.class);
		Method method2 = class1.getMethod("toString");
		method.invoke(object, "woyeshixiaoheshang");
		System.out.println(method2.invoke(object));

	}

	/*
	 * 暴力访问私有的方法
	 */
	public static void function007() throws Exception {
		// 1， 获取Class对象
		Class<?> class1 = Class.forName("com.demo.People");
		// 2,获取构造方法
		Constructor<?> constructor = class1.getConstructor(long.class, String.class);
		// 3，通过构造方法，创建对象
		Object object = constructor.newInstance(12, "xiaozhu");
		// 4，获取指定的方法
		Method method = class1.getDeclaredMethod("function3", String.class);
		// 开启暴力访问
		method.setAccessible(true);
		// 6，执行找到的方法
		method.invoke(object, "小猪");
	}

	/*
	 * 泛型擦除
	 */
	public static void function08() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(12);
		// 将已存在的ArrayList<Integer>集合中添加一个字符串数据，如何做到呢？
		// 其实程序编译后产生的.class文件中是没有泛型约束的，所以我们可以通过反射直接获取到list的class文件对象，绕开泛型
		Class<? extends List> class1 = list.getClass();
		Method method = class1.getMethod("add", Object.class);
		method.invoke(list, "你好，我把字符串加进去了");
		System.out.println(list);

	}

	public static void main(String[] args) throws Exception {
		function08();

	}

}
