package com.uml.contradiction.gui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.AttributeObj;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

public class HotKeyBinder {
	public static void addComponent(JComponent component) {
		KeyStroke loadXMI = KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK);
		String sLoadXMI = "loadXMI";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(loadXMI, sLoadXMI);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(loadXMI, sLoadXMI);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(loadXMI,
				sLoadXMI);
		component.getActionMap().put(sLoadXMI, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelsController.mainWindow.getMyMenuBar().getLoadXMI()
						.doClick();
			}
		});

		KeyStroke exit = KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK);
		String sExit = "exit";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(exit, sExit);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(exit, sExit);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(exit,
				sExit);
		component.getActionMap().put(sExit, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelsController.mainWindow.getMyMenuBar().getExit().doClick();
			}
		});

		KeyStroke verify = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
				KeyEvent.CTRL_MASK);
		String sverify = "verify";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(verify, sverify);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(verify, sverify);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(verify,
				sverify);
		component.getActionMap().put(sverify, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelsController.contradictionsPanel.getVerify().doClick();
			}
		});
		
		KeyStroke debug = KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_MASK);
		String sdebug = "debug";
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(debug, sdebug);
		component.getInputMap(JComponent.WHEN_FOCUSED).put(debug, sdebug);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(debug,
				sdebug);
		component.getActionMap().put(sdebug, new AbstractAction() {
			private static final long serialVersionUID = 3316040008531146448L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ctrl + q " + ClassGraph.getClasses().size() + " " + ObjectGraph.getObjects().size());
				for(CClass cl : ClassGraph.getClasses()){
					System.out.println(cl.getName());
					for(Attribute at : cl.getAttributes()){
						System.out.println("\t" + at.getName() + "\t" + at.getType());
					}
				}
				for(OObject ob : ObjectGraph.getObjects()){
					System.out.println(ob.getName());
					for(AttributeObj ao : ob.getAttributes()){
						System.out.println("\t" + ao.getName() + "\t" + ao.getValue());
					}
				}
			}
		});
	}
}
