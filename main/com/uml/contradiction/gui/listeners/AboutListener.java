package com.uml.contradiction.gui.listeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.Images;
import com.uml.contradiction.gui.Properties;

public class AboutListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		doit();
	}

	public void doit() {
		JDialog dialog = new JDialog(Client.getMainWindow(), true);
		dialog.setLayout(null);
		dialog.setSize(300, 180);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("About");

		String text = "UML Contradiction " + Properties.getVersion() + "\n"
				+ "tool was created by\n" + "Zaretska I.T.\n"
				+ "Kulankhina O.O.\n" + "Shatalov Y.V.\n" + "Mykhailenko H.H.";
		JTextPane txt = new JTextPane();
		txt.setEditable(false);
		txt.setFocusable(false);
		StyledDocument doc = txt.getStyledDocument();
		SimpleAttributeSet sas = new SimpleAttributeSet();
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, text.length(), sas, false);
		txt.setText(text);
		txt.setOpaque(false);
		txt.setBounds(120, 10, 170, 160);
		dialog.add(txt);
		ImageIcon image = Images.getImageIcon("images/hamsters.jpg");
		Image i = image.getImage();
		Image newi = i.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newi);
		JLabel img = new JLabel(newIcon);
		img.setBounds(10, 10, 100, 100);
		dialog.add(img);
		dialog.setVisible(true);
	}
}
