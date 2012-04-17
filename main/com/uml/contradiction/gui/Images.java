package com.uml.contradiction.gui;


import java.io.File;

import javax.swing.ImageIcon;

public class Images {
	 public static ImageIcon failIcon = new ImageIcon("images"+File.separator+"fail.png");
	 public static ImageIcon goodIcon = new ImageIcon("images"+File.separator+"good.png");
	 
	 public static ImageIcon getCriterionImage(String imageName) {
//		 return new ImageIcon(imageName.getClass().getResource("images"+File.separator+"criterion"+File.separator + imageName + ".png"));
		 return new ImageIcon("images"+File.separator+"criterion"+File.separator + imageName + ".png");
	 }
}
