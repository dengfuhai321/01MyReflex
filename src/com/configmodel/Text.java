package com.configmodel;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;

/*
 * ����ڲ��޸Ĵ��������£����ʹ��Person,Student,Worder�ķ�����
 * ������ǰ�������ļ���д��ȫ�����ͷ�����Ȼ�����÷��䶯̬�Ļ�ȡ���ö��󷽷����У���ʱֻ��Ҫ�޸������ļ��Ϳ�����
 * !!!!!!!�����ļ���properties�ļ�ֵ�Բ����ԣ���Ž�β�����򣻷��Ҳ����������
 */
public class Text {
	public static void main(String[] args) throws Exception {
		// io����ȡ�����ļ�
		FileReader reader = new FileReader("config.properties");
		// �������϶���
		Properties pro = new Properties();
		// ���øü��϶��󣬴���io��
		pro.load(reader);
		// �ر�io��
		reader.close();
		// ͨ�������ļ��ļ�ֵ�Ի�ȡֵ
		String className = pro.getProperty("ClassName");
		String FunctionName = pro.getProperty("functionName");
		// ��ȡ�����ļ�����
		Class c = Class.forName(className);
		Object obj = c.newInstance();
		Method method = c.getMethod(FunctionName);
		method.invoke(obj);
	}

}
