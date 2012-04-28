package com.uml.contradiction.gui.windows;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.uml.contradiction.gui.listeners.ExitListener;
import com.uml.contradiction.gui.menu.MyMenuBar;

public class MainWindow extends JFrame {
	public MainWindow() {
		this.setTitle("UML contradictions");
		this.setSize(730, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
//		this.setLocation(100, 300);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ExitListener exitListener = new ExitListener();
				exitListener.doit();
			}
			
		});
		MyMenuBar menuBar = new MyMenuBar();
		this.setJMenuBar(menuBar);
		
	}
}
