package com.uml.contradiction.gui.components;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.uml.contradiction.gui.Client;
import com.uml.contradiction.model.MetaData;

public class ProjectInfoDialog extends JDialog {
	private static final long serialVersionUID = 2799634490356510205L;

	private JLabel projectName;
	private JLabel exporter;
	private JLabel author;
	private JLabel company;
	private JLabel description;
	private JLabel pmCreateDateTime;
	private JLabel pmLastModified;

	private JLabel tprojectName;
	private JLabel texporter;
	private JLabel tauthor;
	private JLabel tcompany;
	private JLabel tdescription;
	private JLabel tpmCreateDateTime;
	private JLabel tpmLastModified;

	public ProjectInfoDialog() {
		super(Client.getMainWindow(), true);
		setLayout(null);
		setSize(400, 260);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Project Information");

		tprojectName = new JLabel("Project name: ");
		tprojectName.setBounds(10, 10, 100, 20);
		tprojectName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tprojectName);

		projectName = new JLabel(MetaData.getProjectName());
		projectName.setBounds(120, 10, 260, 20);
		setSomeSets(projectName);
		add(projectName);

		texporter = new JLabel("Exporter: ");
		texporter.setBounds(10, 40, 100, 20);
		texporter.setHorizontalAlignment(SwingConstants.RIGHT);
		add(texporter);

		exporter = new JLabel(MetaData.getExporter());
		exporter.setBounds(120, 40, 260, 20);
		setSomeSets(exporter);
		add(exporter);

		tauthor = new JLabel("Author: ");
		tauthor.setBounds(10, 70, 100, 20);
		tauthor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tauthor);

		author = new JLabel(MetaData.getAuthor());
		author.setBounds(120, 70, 260, 20);
		setSomeSets(author);
		add(author);

		tcompany = new JLabel("Company: ");
		tcompany.setBounds(10, 100, 100, 20);
		tcompany.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tcompany);

		company = new JLabel(MetaData.getCompany());
		company.setBounds(120, 100, 260, 20);
		setSomeSets(company);
		add(company);

		tdescription = new JLabel("Description: ");
		tdescription.setBounds(10, 130, 100, 20);
		tdescription.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tdescription);

		description = new JLabel(MetaData.getDescription());
		description.setBounds(120, 130, 260, 20);
		setSomeSets(description);
		add(description);

		tpmCreateDateTime = new JLabel("Created date: ");
		tpmCreateDateTime.setBounds(10, 160, 100, 20);
		tpmCreateDateTime.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tpmCreateDateTime);

		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");

		pmCreateDateTime = new JLabel();
		pmCreateDateTime.setBounds(120, 160, 260, 20);
		setSomeSets(pmCreateDateTime);
		add(pmCreateDateTime);

		try {
			calendar.setTimeInMillis(Long.parseLong(MetaData
					.getPmCreateDateTime()));
			pmCreateDateTime.setText(dateFormat.format(calendar.getTime()));
		} catch (NumberFormatException e) {
		}

		tpmLastModified = new JLabel("Last modified: ");
		tpmLastModified.setBounds(10, 190, 100, 20);
		tpmLastModified.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tpmLastModified);

		pmLastModified = new JLabel();
		pmLastModified.setBounds(120, 190, 260, 20);
		setSomeSets(pmLastModified);
		add(pmLastModified);
		try {
			calendar.setTimeInMillis(Long.parseLong(MetaData
					.getPmLastModified()));
			pmLastModified.setText(dateFormat.format(calendar.getTime()));
		} catch (NumberFormatException e) {
		}
	}

	private void setSomeSets(JLabel label) {
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	}
}
