package com.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * ͨ�������ȡ��class�ļ�����Ĺ��췽���������й��췽��
 */
public class Demo1 {
	/**
	 * ��ȡһ�����class�ļ���������ַ�ʽ ������ؼ��ص�ÿ��������==�ģ���
	 */
	public static void function() throws Exception {
		// 1.�����ȡ
		People p = new People();
		// ����People�ĸ��෽��getclass����
		Class c = p.getClass();

		// 2.������ȡ
		Class c1 = People.class;
		// 3.Class��ľ�̬������ȡ forName������.������
		Class c2 = Class.forName("com.demo.People");

	}

	/*
	 * ��ÿղι��췽��
	 */
	public static void function01() throws Exception {
		// 1.��ȡpeople����ļ�����
		Class c = Class.forName("com.demo.People");
		// 2.ֻ�ܻ�ȡ����public���ε����й��췽��
		Constructor[] constructors = c.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
		// ��ȡ�ղι��췽��
		Constructor con = c.getConstructor();// ����ֻ�ܻ��public���εĹ��췽�������������η������У�defaultҲ����
		System.out.println(con);
	}

	/*
	 * ����вι��췽��
	 */
	public static void youcanfunction() throws Exception {
		// ��ȡPeople�ļ�����
		Class<?> class1 = Class.forName("com.demo.People");
		// ��ȡpeople�вι��췽��,����Ҫ����Ӧ���͵����ļ�����
		Constructor<?> constructor = class1.getConstructor(String.class, int.class);
		System.out.println(constructor);
		People instance = (People) constructor.newInstance("xiaodeng", 100);
		System.out.println(instance);

	}

	/*
	 * ֱ�����й��췽��
	 */
	public static void function03() throws Exception {
		Class c = Class.forName("com.demo.People");
		// ֱ�����й��췽��
		Object o = c.newInstance();// ������Ȼ����û�й涨������public��������Ҫ���й��췽�����������Ȩ��
		System.out.println(o);
	}

	/*
	 * �����ȡ˽�еĹ��췽������ ���Ƽ�,�ƻ��˳���ķ�װ��,��ȫ�� ��������
	 */
	public static void huoqusiyoudefunction() throws Exception {
		Class<?> forName = Class.forName("com.demo.People");
		// *��ȡ���еĹ��췽��������˽�е�
		Constructor<?>[] declaredConstructors = forName.getDeclaredConstructors();
		for (Constructor<?> constructor : declaredConstructors) {
			System.out.println(constructor);
		}
		// ��ȡָ�������б�Ĺ��췽��,����˽�е�private
		Constructor<?> declaredConstructor = forName.getDeclaredConstructor(int.class, String.class);
		System.out.println(declaredConstructor);
	}

	/*
	 * �����ȡpublic ��Ա������ֵ�����޸�ֵ
	 */
	public static void function05() throws Exception {
		Class c = Class.forName("com.demo.People");
		// ��ȡ��Ա���� class�ļ��е�����!!!!!!!public �����ĳ�Ա����
		Field[] fields = c.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		// ��ȡָ���ĳ�Ա����
		Field field2 = c.getField("name");
		System.out.println(field2);
		Object object = c.newInstance();
		field2.set(object, "xiaodeng");
		System.out.println(object);

	}

	/*
	 * �����ȡ��Ա������ֵ�����޸�ֵ
	 */
	public static void function005() throws Exception {
		Class c = Class.forName("com.demo.People");
		// ��ȡ��Ա���� class�ļ��е����еĳ�Ա����,����˽�е�
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		// ��ȡָ���ĳ�Ա����
		Field field2 = c.getDeclaredField("name");
		System.out.println(field2);
		Object object = c.newInstance();
		field2.set(object, "xiaodeng");// ��object�������ֵ
		System.out.println(field2.get(object));// �õ�object�������ó�Աname������ֵ
		System.out.println(object);

	}

	/*
	 * �����ȡ��Ա����
	 */
	public static void function06() throws Exception {
		Class c = Class.forName("com.demo.People");
		// ���ٻ�ȡ����
		Object o = c.newInstance();
		// ��ȡ�����븸��������public ���εķ���
		Method[] methods = c.getMethods();
		for (Method string : methods) {
			System.out.println(string);
		}
		// ��ȡָ����public���εķ�������
		Method method = c.getMethod("action");
		System.out.println(method);

	}

	/*
	 * ��ȡ���������еķ���(����˽�е�)
	 */
	public static void function006() throws Exception {
		Class c = Class.forName("com.demo.People");
		// ���ٻ�ȡ����
		Object o = c.newInstance();
		// ��ȡ�����븸�������еķ���
		Method[] methods = c.getDeclaredMethods();
		for (Method string : methods) {
			System.out.println(string);
		}
		// ��ȡָ���ķ���
		Method method = c.getDeclaredMethod("function1", null);// �������뷽�����ͷ�������
		System.out.println(method);

	}

	/*
	 * ִ���ҵ��ķ���
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
	 * ��������˽�еķ���
	 */
	public static void function007() throws Exception {
		// 1�� ��ȡClass����
		Class<?> class1 = Class.forName("com.demo.People");
		// 2,��ȡ���췽��
		Constructor<?> constructor = class1.getConstructor(long.class, String.class);
		// 3��ͨ�����췽������������
		Object object = constructor.newInstance(12, "xiaozhu");
		// 4����ȡָ���ķ���
		Method method = class1.getDeclaredMethod("function3", String.class);
		// ������������
		method.setAccessible(true);
		// 6��ִ���ҵ��ķ���
		method.invoke(object, "С��");
	}

	/*
	 * ���Ͳ���
	 */
	public static void function08() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(12);
		// ���Ѵ��ڵ�ArrayList<Integer>���������һ���ַ������ݣ���������أ�
		// ��ʵ�������������.class�ļ�����û�з���Լ���ģ��������ǿ���ͨ������ֱ�ӻ�ȡ��list��class�ļ������ƿ�����
		Class<? extends List> class1 = list.getClass();
		Method method = class1.getMethod("add", Object.class);
		method.invoke(list, "��ã��Ұ��ַ����ӽ�ȥ��");
		System.out.println(list);

	}

	public static void main(String[] args) throws Exception {
		function08();

	}

}
