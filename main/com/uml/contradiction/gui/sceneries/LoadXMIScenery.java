package com.uml.contradiction.gui.sceneries;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.uml.contradiction.gui.controllers.PanelsController;

public class LoadXMIScenery {
	private static final Logger LOGGER = Logger.getRootLogger();
	public static void run() throws IOException{
		JFileChooser chooser = new JFileChooser("Load XMI");
		XMIFileFilter xmiFileFilter = new XMIFileFilter();
		chooser.setFileFilter(xmiFileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		//int returnValue = chooser.showOpenDialog(PanelsController.mainWindow);
		int returnValue = chooser.showOpenDialog(frame);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			LOGGER.info("we choosed " + file.getName());
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				long length = file.length();
		        if (length > Integer.MAX_VALUE) {
		        	LOGGER.info("File is too large.");
		        	throw new IOException("File is too large.");
		        }
		        byte[] bytes = new byte[(int)length];
		        int offset = 0;
		        int numRead = 0;
		        while (offset < bytes.length
		               && (numRead = bis.read(bytes, offset, bytes.length-offset)) >= 0) {
		            offset += numRead;
		        }
		        if (offset < bytes.length) {
		        	LOGGER.error("Could not completely read file "+file.getName());
		            throw new IOException("Could not completely read file "+file.getName());
		        }
		        // Close the input stream and return bytes
		        bis.close();
		        return bytes;
			} catch (FileNotFoundException e) {
				LOGGER.error("File: " + chooser.getSelectedFile().getName() + " dissaper");
			}
		}
	}
	public static JFrame frame;
	public static void main(String [] args){
		frame = new JFrame("UML Contradiction");
		frame.setSize(300, 350);
		frame.setLocation(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run();
		System.out.println("Complete!");
	}
}
