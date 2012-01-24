package com.uml.contradiction.gui;

import javax.swing.JFrame;

public class Client {
	public static void main(String [] args){
		JFrame frame = new JFrame("UML Contradiction");
		frame.setSize(300, 350);
		frame.setLocation(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("completed..");
	}
}
