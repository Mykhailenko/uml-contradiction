package com.uml.contradiction.gui;

import java.io.File;
import java.util.List;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.exporters.ResultSaver;
import com.uml.contradiction.exporters.doc.DocExporterJ2W;
import com.uml.contradiction.exporters.txt.TxtExporter;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;
import com.uml.contradiction.gui.sceneries.StartCheckScenery;
import com.uml.contradiction.gui.windows.MainWindow;
import com.uml.contradiction.model.MetaData;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.ObjectGraph;
import com.uml.contradiction.model.sequence.SequenceGraph;
import com.uml.contradiction.model.statemachine.StateMachineGraph;

public class Client implements GUIState {
	private static MainWindow mainWindow;
	private static Client client;
	private static List<VerificationResult> lastResults;

	private enum State {
		START, LOADED_NO_ONE, LOADED_ONE, VERIFIED;
	};

	private State state;

	public static void main(String[] args) {
		if (args.length == 0) {
			client = new Client();
		} else {
			comandLineStyle(args);
		}
	}

	private static void comandLineStyle(String[] args) {
		String resultFileName = "result.doc";
		String xmiFileName;
		if (args.length == 1) {
			xmiFileName = args[0];
			verify(xmiFileName, resultFileName, true);
		} else if (args.length == 2) {
			xmiFileName = args[0];
			resultFileName = args[1];
			verify(xmiFileName, resultFileName, true);
		} else if (args.length == 3) {
			xmiFileName = args[0];
			resultFileName = args[1];
			boolean txtExporter = true;
			if (args[2].equals("-doc")) {
				txtExporter = false;
			}
			verify(xmiFileName, resultFileName, txtExporter);
		} else {
			System.out.println("Unexpected arguments count.");
		}

	}

	private static void verify(String xmiFileName, String resultFileName,
			boolean txtExporter) {
		File file = new File(xmiFileName);
		if (file.exists()) {
			XMIConverter.setFileAndParse(file);
			List<VerificationResult> results = StartCheckScenery
					.verifyCriterions(CriterionSuite.getAllCriterion());
			Exporter exporter;
			if (txtExporter) {
				exporter = new TxtExporter();
			} else {
				exporter = new DocExporterJ2W();
			}
			ResultSaver.savep(results, resultFileName, exporter);
		} else {
			System.out.println("There is no such file : " + xmiFileName);
		}
	}

	public Client() {
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

	public boolean isStart() {
		return state == State.START;
	}

	public boolean isLoadedNoOne() {
		return state == State.LOADED_NO_ONE;
	}

	public boolean isLoadedOne() {
		return state == State.LOADED_ONE;
	}

	public boolean isVerified() {
		return state == State.VERIFIED;
	}

	public static Client getClient() {
		return client;
	}

	public static MainWindow getMainWindow() {
		return mainWindow;
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
		lastResults = null;
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

	public static void setLastResults(List<VerificationResult> lastResults) {
		Client.lastResults = lastResults;
	}

	public static List<VerificationResult> getLastResults() {
		return lastResults;
	}
}
