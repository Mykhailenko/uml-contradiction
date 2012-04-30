package com.uml.contradiction.gui;


import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;
import com.uml.contradiction.gui.windows.MainWindow;
import com.uml.contradiction.model.MetaData;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.ObjectGraph;
import com.uml.contradiction.model.sequence.SequenceGraph;
import com.uml.contradiction.model.statemachine.StateMachineGraph;

public class Client implements GUIState{
	private static boolean xmiLoaded = false;
	private static MainWindow mainWindow;
	private static Client client;
	private enum State {
		START, LOADED_NO_ONE, LOADED_ONE, VERIFIED;
	};
	private State state;
	public static void main(String [] args){
		client = new Client();
		
	}
	public Client(){
		mainWindow = new MainWindow();
		ContradictionsPanel p = new ContradictionsPanel();
		VerificationResultsPanel pp = new VerificationResultsPanel();
		PanelsController.contradictionsPanel = p;
		PanelsController.resultsPanel = pp;
		PanelsController.mainWindow = mainWindow;
		PanelsController.showPanel(p);
		mainWindow.setVisible(true);
		started();
	}
	public boolean isStart(){
		return state == State.START;
	}
	public boolean isLoadedNoOne(){
		return state == State.LOADED_NO_ONE;
	}
	public boolean isLoadedOne(){
		return state == State.LOADED_ONE;
	}
	public boolean isVerified(){
		return state == State.VERIFIED;
	}
	
	public static Client getClient() {
		return client;
	}
	public static MainWindow getMainWindow() {
		return mainWindow;
	}
	public static boolean isXmiLoaded() {
		return xmiLoaded;
	}
	public static void setXmiLoaded(boolean xmiLoaded) {
		Client.xmiLoaded = xmiLoaded;
	}
	@Override
	public void started() {
		PanelsController.contradictionsPanel.started();
		PanelsController.resultsPanel.started();
		PanelsController.mainWindow.started();
		state = State.START;
		ClassGraph.clear();
		ObjectGraph.clear();
		SequenceGraph.clear();
		StateMachineGraph.clear();
		MetaData.clear();
	}
	@Override
	public void loadedNoOneSelected() {
		PanelsController.contradictionsPanel.loadedNoOneSelected();
		PanelsController.resultsPanel.loadedNoOneSelected();
		PanelsController.mainWindow.loadedNoOneSelected();
		state = State.LOADED_NO_ONE;
	}
	@Override
	public void loadedOneSelected() {
		PanelsController.contradictionsPanel.loadedOneSelected();
		PanelsController.resultsPanel.loadedOneSelected();
		PanelsController.mainWindow.loadedOneSelected();
		state = State.LOADED_NO_ONE;
	}
	@Override
	public void verified() {
		PanelsController.contradictionsPanel.verified();
		PanelsController.resultsPanel.verified();
		PanelsController.mainWindow.verified();
		state = State.VERIFIED;
	}
	
}
