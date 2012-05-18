package com.uml.contradiction.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.HotKeyBinder;
import com.uml.contradiction.gui.listeners.diagramsChoise.ComboListener;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.diagramstypes.ViewedDiagramType;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class DiagramsPanel extends JPanel {

	private static final long serialVersionUID = -5152681245083788395L;
	private List<DiagramForChoise> fromDiagrams = new ArrayList<DiagramForChoise>();
	private List<DiagramForChoise> toDiagrams = new ArrayList<DiagramForChoise>();
	private final JButton nextBut = new JButton(
			DiagrPanelVoc.getBtnLabel("nextBut"));
	private final JButton backBut = new JButton(
			DiagrPanelVoc.getBtnLabel("backBut"));
	private final JButton addBut = new JButton(
			DiagrPanelVoc.getBtnLabel("addBut"));
	private final JButton addAllBut = new JButton(
			DiagrPanelVoc.getBtnLabel("addAllBut"));
	private final JButton removeBut = new JButton(
			DiagrPanelVoc.getBtnLabel("removeBut"));
	private final JButton removeAllBut = new JButton(
			DiagrPanelVoc.getBtnLabel("removeAllBut"));
	private final DefaultListModel fromListModel = new DefaultListModel();
	private final DefaultListModel toListModel = new DefaultListModel();

	private final JComboBox combo = new JComboBox();;
	private final JList fromList = new JList();;
	private final JList toList = new JList();;

	public DiagramsPanel() {
		super();
		createGUI();
		HotKeyBinder.addComponent(this);
	}

	private void createGUI() {
		List<Object> diagramsTypes = new ArrayList<Object>();
		diagramsTypes.add("All");
		diagramsTypes.add(new ViewedDiagramType(DiagramType.CLASS, "Class"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.OBJECT, "Object"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.SEQUENCE,
				"Sequence"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.STATE_MACHINE,
				"StateMaschene"));

		final DefaultComboBoxModel comboModel = new DefaultComboBoxModel(
				diagramsTypes.toArray());
		combo.setModel(comboModel);

		fromList.setModel(fromListModel);
		setViewedFromDiagrams(fromDiagrams);
		toList.setModel(toListModel);

		this.combo.addActionListener(new ComboListener());

		this.setLayout(null);
		combo.setBounds(10, 10, 150, 20);
		fromList.setBounds(10, 40, 150, 300);
		addBut.setBounds(10, 350, 70, 30);
		addAllBut.setBounds(90, 350, 70, 30);
		toList.setBounds(170, 40, 150, 300);
		removeBut.setBounds(170, 350, 70, 30);
		removeAllBut.setBounds(250, 350, 70, 30);

		this.add(combo);
		this.add(fromList);
		this.add(addBut);
		this.add(addAllBut);
		this.add(toList);
		this.add(removeBut);
		this.add(removeAllBut);

		this.updateUI();
		this.repaint();
	}

	public void setFromDiagrams(List<DiagramForChoise> newDiagrams) {
		this.fromDiagrams.clear();
		this.fromDiagrams.addAll(newDiagrams);
		setViewedFromDiagrams(fromDiagrams);
		this.combo.setSelectedIndex(0);
	}

	public void setViewedFromDiagrams(List<DiagramForChoise> newDiagrams) {
		this.fromListModel.removeAllElements();
		for (int i = 0; i < newDiagrams.size(); i++) {
			fromListModel.addElement(newDiagrams.get(i));
		}
		this.fromList.updateUI();

		this.updateUI();
		this.repaint();
	}

	public void setViewedToDiagrams(List<DiagramForChoise> newDiagrams) {
		this.toListModel.removeAllElements();
		for (int i = 0; i < newDiagrams.size(); i++) {
			toListModel.addElement(newDiagrams.get(i));
		}
		this.toList.updateUI();

		this.updateUI();
		this.repaint();
	}

	public List<DiagramForChoise> getDiagrams() {
		return fromDiagrams;
	}

	public JButton getNextBut() {
		return nextBut;
	}

	public JButton getBackBut() {
		return backBut;
	}

	public JButton getAddBut() {
		return addBut;
	}

	public JButton getAddAllBut() {
		return addAllBut;
	}

	public JButton getRemoveBut() {
		return removeBut;
	}

	public JButton getRemoveAllBut() {
		return removeAllBut;
	}

	public DefaultListModel getFromListModel() {
		return fromListModel;
	}

	public DefaultListModel getToListModel() {
		return toListModel;
	}

	public JComboBox getCombo() {
		return combo;
	}

	public JList getFromList() {
		return fromList;
	}

	public JList getToList() {
		return toList;
	}

	public List<DiagramForChoise> getToDiagrams() {
		return toDiagrams;
	}

	public void setToDiagrams(List<DiagramForChoise> toDiagrams) {
		this.toDiagrams = toDiagrams;
	}

	public List<DiagramForChoise> getFromDiagrams() {
		return fromDiagrams;
	}

}