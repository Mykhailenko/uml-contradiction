package com.uml.contradiction.gui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.sequence.SequenceGraph;

public class HotKeyBinder {
	private static final Logger LOGGER = Logger.getRootLogger();
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
				InputEvent.CTRL_MASK);
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
				if(Client.getClient().isVerified()){
					PanelsController.resultsPanel.getBackBut().doClick();
				}else{
					PanelsController.contradictionsPanel.getVerify().doClick();
				}
			}
		});

		KeyStroke debug = KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK);
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
				for(Interaction in : SequenceGraph.getInteractions()){
					LOGGER.error("INTERACTION " + in.getName());
					for(InteractionElement child : in.getChilds()){
						if(child.getType().equals(InteractionElement.Type.MESSAGE)){
							Message message = (Message) child;
							LOGGER.error(message.getOriginalString() + " m :" + message.getMethodName() + " p:" + message.getParamCount());
						}
					}
				}
				
//				for(StateMachine sm : StateMachineGraph.getStateMachines()){
//					for(Transition tr : sm.getTransitions()){
//						LOGGER.error(tr.toString());
//					}
//				}
				
//				System.out.println("ctrl + q " + ClassGraph.getClasses().size()
//						+ " " + ObjectGraph.getObjects().size());
//				for (CClass cl : ClassGraph.getClasses()) {
//					System.out.println(cl.getName());
//					for (Attribute at : cl.getAttributes()) {
//						System.out.println("\t" + at.getName() + "\t"
//								+ at.getType());
//					}
//				}
//				System.out.println("objects");
//				for (OObject ob : ObjectGraph.getObjects()) {
//					System.out.println(ob.getName());
//					for (AttributeObj ao : ob.getAttributes()) {
//						System.out.println("\t" + ao.getName() + "\t"
//								+ ao.getValue());
//					}
//				}
//				System.out.println("files");
//				String list[] = new File(".").list();
//				for (int i = 0; i < list.length; i++) {
//					System.out.println(list[i]);
//				}
			}
		});
	}
}
