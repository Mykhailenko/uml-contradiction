package com.uml.contradiction.gui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.uml.contradiction.gui.controllers.PanelsController;

public class HotKeyBinder {
	public static void addComponent(JComponent component){
		KeyStroke loadXMI = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		String sLoadXMI = "loadXMI";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(loadXMI, sLoadXMI);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(loadXMI, sLoadXMI);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(loadXMI, sLoadXMI);
		component.getActionMap().put(sLoadXMI, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelsController.mainWindow.getMyMenuBar().getLoadXMI().doClick();
			}
		});
		
		KeyStroke exit = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK);
		String sExit = "exit";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(exit, sExit);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(exit, sExit);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(exit, sExit);
		component.getActionMap().put(sExit, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelsController.mainWindow.getMyMenuBar().getExit().doClick();
			}
		});
	}
}





















