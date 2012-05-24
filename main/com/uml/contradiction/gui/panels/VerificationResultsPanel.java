package com.uml.contradiction.gui.panels;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.HotKeyBinder;
import com.uml.contradiction.gui.components.ResCellRenderer;
import com.uml.contradiction.gui.listeners.FailDiagrListListener;
import com.uml.contradiction.gui.listeners.ResPanelBackListener;
import com.uml.contradiction.gui.listeners.ResultsTreeListener;
import com.uml.contradiction.gui.models.DisplayedCriterion;

public class VerificationResultsPanel extends JPanel implements GUIState {
	private static final long serialVersionUID = 8430037781170755259L;
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();

	StyleContext sc = new StyleContext();
	final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	private final JTextPane description = new JTextPane(doc);
	private Style mainStyle;
	private Style elementStyle;
	private Style heading2Style;

	private final DefaultListModel listModel = new DefaultListModel();
	private Map<Criterion, VerificationResult> results;
	private Map<String, List<String>> descrByDiagrams = new HashMap<String, List<String>>();
	private final JList diagrams = new JList(listModel);

	// private Map<Criterion, List<ResultTemplate> > resultsDescr = new
	// HashMap<Criterion, List<ResultTemplate> >();

	public VerificationResultsPanel() {
		super();
		createGUI();
		HotKeyBinder.addComponent(this);
	}

	private void createGUI() {
		this.setLayout(null);
		this.setStyles();

		JScrollPane treePanel = new JScrollPane(this.tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		JScrollPane listPanel = new JScrollPane(diagrams);

		this.tree.addTreeSelectionListener(new ResultsTreeListener());
		this.tree.setCellRenderer(new ResCellRenderer());

		this.backBut.addActionListener(new ResPanelBackListener());
		backBut.setToolTipText("Back (Ctrl + Enter)");
		this.diagrams.addListSelectionListener(new FailDiagrListListener());

		// this.description//.setLineWrap(true);

		treePanel.setBounds(10, 20, 360, 480);
		descriptionPanel.setBounds(380, 160, 400, 340);
		backBut.setBounds(10, 510, 100, 25);
		listPanel.setBounds(380, 20, 400, 120);

		this.add(listPanel);
		this.add(treePanel);
		this.add(descriptionPanel);
		this.add(backBut);

		JLabel label0 = new JLabel("Criterions:");
		label0.setBounds(10, 0, 400, 20);
		this.add(label0);

		JLabel label1 = new JLabel("Diagrams:");
		label1.setBounds(380, 0, 400, 20);
		this.add(label1);

		JLabel label2 = new JLabel("Description:");
		label2.setBounds(380, 140, 400, 20);
		this.add(label2);

	}

	public void setSelectedDiagrams(DefaultMutableTreeNode root) {
		this.tree.setModel(new DefaultTreeModel(root));
		this.tree.updateUI();
		this.tree.repaint();
		this.updateUI();
		this.repaint();

		return;
	}

	public Map<Criterion, VerificationResult> getResults() {
		return results;
	}

	public void setResults(Map<Criterion, VerificationResult> results) {
		this.results = results;
	}

	public void showResult(DefaultMutableTreeNode node) {
		System.out.println("Showing result");
		this.listModel.removeAllElements();
		this.descrByDiagrams.clear();
		this.description.setText("");

		if (!node.isLeaf()) {
			this.description.setText("");

		} else {
			System.out.println("Showing result");

			Criterion crit = (((DisplayedCriterion) node.getUserObject())
					.getCriterion());
			VerificationResult res = results.get(crit);

			if (res.isGood()) {
				this.description.setText("Everything is correct");
			} else {

				List<ResultTemplate> descriptions = res.getResultTemplate();

				for (ResultTemplate rt : descriptions) {
					String diagrams = rt.getDiagramsNames();
					String description = rt.getDescription();

					if (!this.descrByDiagrams.containsKey(diagrams)) {
						List<String> descr = new LinkedList<String>();
						System.out.println(descr);
						descr.add(description);
						System.out.println("PUTTED: " + diagrams + " "
								+ description);
						this.descrByDiagrams.put(diagrams, descr);

						this.listModel.addElement(diagrams);
					} else {
						List<String> descr = this.descrByDiagrams.get(diagrams);
						System.out.println("PUTTED: " + diagrams + " "
								+ description);
						descr.add(description);
					}
				}
			}
		}
		this.diagrams.updateUI();
		this.diagrams.repaint();
		this.updateUI();
		this.repaint();

		return;
	}

	public void showDescription(List<String> descriptions) {
		this.description.setText("");
		String text = new String();
		String contrText = "Contradiction ";
		String contrSeparator = "\n\n";

		for (int i = 0; i < descriptions.size(); i++) {
			text = text + contrText + String.valueOf(i + 1) + ":\n";
			String[] parts = descriptions.get(i).split(
					ResultTemplate.ELEMENT_MARKER);
			for (int j = 0; j < parts.length; j++) {
				text = text + parts[j];
			}
			text = text + contrSeparator;
		}

		doc.setLogicalStyle(0, mainStyle);
		try {
			doc.insertString(0, text, null);

			int curIndex = 0;
			for (int i = 0; i < descriptions.size(); i++) {
				int headerLen = (contrText + String.valueOf(i + 1) + ":\n")
						.length();

				doc.setCharacterAttributes(curIndex, headerLen, heading2Style,
						false);
				curIndex = curIndex + headerLen;

				String[] descrParts = descriptions.get(i).split(
						ResultTemplate.ELEMENT_MARKER);
				System.out.println(descrParts);
				boolean isElement = false;
				for (int j = 0; j < descrParts.length; j++) {
					int partLen = descrParts[j].length();
					if (isElement) {
						doc.setCharacterAttributes(curIndex, partLen,
								elementStyle, false);
						curIndex = curIndex + partLen;
						isElement = false;
					} else {
						doc.setCharacterAttributes(curIndex, partLen,
								mainStyle, false);
						curIndex = curIndex + partLen;
						isElement = true;
					}
				}
				curIndex += contrSeparator.length();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		this.description.updateUI();
		this.updateUI();
		this.repaint();
	}

	public VerificationResult getResult(Criterion crit) {
		return this.results.get(crit);
	}

	public Map<String, List<String>> getDescrByDiagrams() {
		return descrByDiagrams;
	}

	public void setDescrByDiagrams(Map<String, List<String>> descrByDiagrams) {
		this.descrByDiagrams = descrByDiagrams;
	}

	public JList getDiagrams() {
		return diagrams;
	}

	@Override
	public void started() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadedNoOneSelected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadedOneSelected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void verified() {
		// TODO Auto-generated method stub

	}

	public void setStyles() {
		this.mainStyle = sc.addStyle("MainStyle",
				sc.getStyle(StyleContext.DEFAULT_STYLE));
		this.elementStyle = sc.addStyle("Elements", null);
		this.heading2Style = sc.addStyle("Heading2", null);

		// Create and add the main document style
//		Style defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setLeftIndent(mainStyle, 2);
		StyleConstants.setRightIndent(mainStyle, 2);
		StyleConstants.setFirstLineIndent(mainStyle, 4);
		StyleConstants.setFontFamily(mainStyle, "arial");
		StyleConstants.setFontSize(mainStyle, 14);

		// Create and add the constant width style

		StyleConstants.setFontFamily(elementStyle, "arial");
		StyleConstants.setForeground(elementStyle, Color.blue);
		StyleConstants.setFontSize(elementStyle, 14);
		StyleConstants.setBold(elementStyle, true);
		// Create and add the heading style

		StyleConstants.setForeground(heading2Style, Color.red);
		StyleConstants.setFontSize(heading2Style, 16);
		StyleConstants.setFontFamily(heading2Style, "arial");
		StyleConstants.setBold(heading2Style, true);
		StyleConstants.setLeftIndent(heading2Style, 8);
		StyleConstants.setFirstLineIndent(heading2Style, 0);
	}
	public JButton getBackBut() {
		return backBut;
	}
}
