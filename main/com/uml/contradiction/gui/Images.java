package com.uml.contradiction.gui;


import javax.swing.ImageIcon;

public class Images {
	 public static ImageIcon failIcon = new ImageIcon("images/fail.png");
	 public static ImageIcon goodIcon = new ImageIcon("images/good.png");
	 
	 public static ImageIcon getCriterionImage(String imageName) {
		 return new ImageIcon("images/criterion/" + imageName + ".png");
	 }
}
