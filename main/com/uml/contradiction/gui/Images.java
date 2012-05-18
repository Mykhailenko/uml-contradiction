package com.uml.contradiction.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class Images {
	public static ImageIcon failIcon = getImageIcon("images/fail.png");
	public static ImageIcon goodIcon = getImageIcon("images/good.png");

	public static ImageIcon getCriterionImage(String imageName) {
		return getImageIcon("images/criterion/" + imageName + ".png");
	}

	public static ImageIcon getImageIcon(String path) {
		URL url = Client.getClient().getClass().getClassLoader()
				.getResource(path);
		if (url != null) {
			Image image = Toolkit.getDefaultToolkit().getImage(url);
			return new ImageIcon(image);// for running in jar file
		} else {
			return new ImageIcon(path);// for running in eclipse
		}
	}
}
