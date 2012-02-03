package com.uml.contradiction.gui.sceneries;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XMIFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		if(file.isDirectory()){
			return true;
		}
		if(getExtension(file).equalsIgnoreCase("xmi")){
			return true;
		}else{
			return false;
		}
	}
	private String getExtension(File file){
		int point = file.getName().lastIndexOf('.');
		if(point >= 0 && file.getName().length() > point+1){
			return file.getName().substring(point+1);
		}else{
			return new String();
		}
	}
	@Override
	public String getDescription() {
		return "XMI Files";
	}

}
