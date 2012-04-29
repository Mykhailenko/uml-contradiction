package com.uml.contradiction.gui;

import java.io.FileInputStream;

public class Properties {
	static private java.util.Properties properties;
	static{
		properties = new java.util.Properties();
		try {
			properties.load(new FileInputStream("build.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getVersion(){
		return (String) properties.get("version");
	}
	
	
}
