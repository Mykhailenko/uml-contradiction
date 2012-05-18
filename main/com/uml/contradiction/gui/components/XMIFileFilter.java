package com.uml.contradiction.gui.components;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XMIFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return isAcceptableExtension(getExtension(file));
	}

	private boolean isAcceptableExtension(String extension) {
		String[] acceptableExtensions = { "xmi", "uml" };
		for (String ac : acceptableExtensions) {
			if (ac.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
	}

	private String getExtension(File file) {
		int point = file.getName().lastIndexOf('.');
		if (point >= 0 && file.getName().length() > point + 1) {
			return file.getName().substring(point + 1);
		} else {
			return new String();
		}
	}

	@Override
	public String getDescription() {
		return "XMI Files";
	}

}
