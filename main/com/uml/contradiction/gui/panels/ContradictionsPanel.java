package com.uml.contradiction.gui.panels;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.CriterionTypeSuite;
import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.HotKeyBinder;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.listeners.ContrTreeListener;
import com.uml.contradiction.gui.listeners.StartCheckListener;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.models.DisplayedCriterionType;
import com.uml.contradiction.gui.sceneries.StartCheckScenery;

public class ContradictionsPanel extends JPanel implements GUIState {
	private static final long serialVersionUID = 4294444439402447738L;
	private final JButton verify = new JButton("Verify");
	private final JTree tree = new JTree();
	private final JTextArea description = new JTextArea();
	private CheckTreeManager checkTreeManager;
	private final JLabel imgLbl = new JLabel();
	private JLabel numOfSelected;

	public ContradictionsPanel() {
		super();
		createGUI();
	}

	private void createGUI() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Criterions");
		DefaultTreeModel model = new DefaultTreeModel(root);

		List<DisplayedCriterion> criterions = CriterionSuite
				.getDisplayedCriterions();
		List<DisplayedCriterionType> types = CriterionTypeSuite
				.getAllDisplayedCriterions();

		for (DisplayedCriterionType type : types) {
			DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(type);
			root.add(typeNode);

			for (DisplayedCriterion crit : criterions) {
				if (crit.getType().equals(type.getType())) {
					typeNode.add(new DefaultMutableTreeNode(crit));
				}
			}
		}

		tree.addTreeSelectionListener(new ContrTreeListener());
		tree.setModel(model);
		this.checkTreeManager = new CheckTreeManager(tree);
		JLabel selectLabel = new JLabel("Criterions:");
		selectLabel.setBounds(10, 0, 400, 20);
		this.add(selectLabel);

		JScrollPane treePanel = new JScrollPane(tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		description.setEditable(false);
		description.setOpaque(false);

		descriptionPanel.setBorder(null);
		descriptionPanel
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionPanel
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		description.setBorder(null);
		description.setWrapStyleWord(true);
		description.setLineWrap(true);
		descriptionPanel.setOpaque(false);
		verify.addActionListener(new StartCheckListener());
		verify.setToolTipText("Verify (Ctrl + Enter)");
		imgLbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		imgLbl.setBackground(Color.white);
		imgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl.setOpaque(true);

		this.setLayout(null);
		treePanel.setBounds(10, 20, 360, 480);
		JLabel desLabel = new JLabel("Description:");
		desLabel.setBounds(380, 0, 400, 20);
		this.add(desLabel);
		descriptionPanel.setBounds(380, 20, 400, 120);
		description.setBounds(descriptionPanel.getBounds());
		JLabel imgLabel = new JLabel("Image:");
		imgLabel.setBounds(380, 140, 400, 20);
		this.add(imgLabel);
		imgLbl.setBounds(380, 160, 400, 340);
		verify.setBounds(10, 510, 100, 25);
		this.add(treePanel);
		this.add(descriptionPanel);
		this.add(verify);
		this.add(imgLbl);

		this.updateUI();
		this.repaint();

		numOfSelected = new JLabel("0 / "
				+ CriterionSuite.getDisplayedCriterions().size() + " selected");
		numOfSelected.setBounds(220, 510, 150, 25);
		this.add(numOfSelected);

		HotKeyBinder.addComponent(this);
	}

	public JButton getVerify() {
		return verify;
	}

	public static int[] getScalableWidthAndHeight(ImageIcon image) {
		float w = 400;
		float h = 340;
		int[] result = new int[2];
		float k = image.getIconWidth() / image.getIconHeight();
		float k1 = w / h;
		if (image.getIconWidth() >= image.getIconHeight()) {
			if (k >= k1) {
				result[0] = (int) w;
				result[1] = (int) (w / k);
			} else {
				result[0] = (int) (h * k);
				result[1] = (int) h;
			}
		} else {
			result[0] = (int) (h * k);
			result[1] = (int) h;
		}
		return result;
	}

	public void showDescription(Object object) {

		if (object instanceof DisplayedCriterion) {
			this.description.setText(((DisplayedCriterion) object)
					.getDesctiption());
			this.description.repaint();
			this.description.updateUI();

			// this.imgLbl.setIcon(((DisplayedCriterion)object).getImg());
			ImageIcon image = ((DisplayedCriterion) object).getImg();
			Image i = image.getImage();
			int[] sca = getScalableWidthAndHeight(image);
			Image newi = i
					.getScaledInstance(sca[0], sca[1], Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newi);
			imgLbl.setIcon(newIcon);
			this.imgLbl.updateUI();
			this.imgLbl.repaint();

			this.updateUI();
		} else {
			this.description.setText("");
			this.description.repaint();
			this.description.updateUI();
			this.imgLbl.setIcon(null);
			this.imgLbl.updateUI();
			this.imgLbl.repaint();
			this.updateUI();
		}
	}

	public List<DefaultMutableTreeNode> getSelectedNodes() {
		List<DefaultMutableTreeNode> res = new LinkedList<DefaultMutableTreeNode>();
		TreePath[] paths = checkTreeManager.getSelectionModel()
				.getSelectionPaths();

		if (paths == null) {
			return res;
		}

		for (TreePath path : paths) {
			res.add(((DefaultMutableTreeNode) path.getLastPathComponent()));
		}

		return res;
	}

	public JTree getTree() {
		return tree;
	}

	public String stringForLabelNumOfSelected() {
		return "" + numberOfSelected() + " / "
				+ CriterionSuite.getDisplayedCriterions().size() + " selected";
	}

	public int numberOfSelected() {
		ContradictionsPanel panel = PanelsController.contradictionsPanel;
		List<DefaultMutableTreeNode> nodes = panel.getSelectedNodes();
		if (nodes == null || nodes.size() == 0) {
			return 0;
		} else {
			return StartCheckScenery.countOfSelectedCriterions(nodes);
		}
	}

	public void updateState() {
		numOfSelected.setText(stringForLabelNumOfSelected());
	}

	@Override
	public void started() {
		verify.setEnabled(false);
		checkTreeManager.getSelectionModel().clearSelection();

	}

	@Override
	public void loadedNoOneSelected() {
		verify.setEnabled(false);
	}

	@Override
	public void loadedOneSelected() {
		verify.setEnabled(true);
	}

	@Override
	public void verified() {
		verify.setEnabled(false);

	}

}
