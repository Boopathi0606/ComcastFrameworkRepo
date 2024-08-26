package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;

import java.util.Properties;

import org.testng.annotations.Test;


public class FileUtility {
	public String getDataFromPropertiesFile(String key) throws Throwable {
	
	FileInputStream fis=new FileInputStream("./configAppData/CommonData.properties");
	Properties pObj=new Properties();
	pObj.load(fis);
	String data=pObj.getProperty(key);
	return data;
}
}
 class A{
	@Test
	public void test() throws Throwable {
		FileUtility futil = new FileUtility();
		System.out.println( futil.getDataFromPropertiesFile("url") );
	}
}
