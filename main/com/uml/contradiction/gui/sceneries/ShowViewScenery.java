package com.uml.contradiction.gui.sceneries;

import com.uml.contradiction.gui.controllers.PanelsController;

public class ShowViewScenery {
	public static void run() {
		PanelsController.showPanel(PanelsController.startPanel);
		PanelsController.mainWindow.setVisible(true);
	}
}
