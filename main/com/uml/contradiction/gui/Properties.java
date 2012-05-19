package com.uml.contradiction.gui;

import java.io.File;
import java.io.FileInputStream;

public class Properties {
	private static  java.util.Properties properties;
	static {
		properties = new java.util.Properties();
		try {
			String path = "build.properties";
			File file = new File(path);
			if (file.exists()) {
				properties.load(new FileInputStream(file));
			} else {
				properties.load(Properties.class.getClassLoader()
						.getResourceAsStream(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getVersion() {
		return (String) properties.get("version");
	}

	public static String getApplicationName() {
		return (String) properties.get("application-name");
	}

	public static String getEmail() {
		return (String) properties.get("e-mail");
	}

}
