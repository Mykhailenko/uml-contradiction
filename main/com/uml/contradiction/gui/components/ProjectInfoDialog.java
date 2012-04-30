package com.uml.contradiction.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;

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
		tprojectName.setHorizontalAlignment(JLabel.RIGHT);
		add(tprojectName);
		
		projectName = new JLabel(MetaData.getProjectName());
		projectName.setBounds(120, 10, 260, 20);
		setSomeSets(projectName);
		add(projectName);
		
		texporter = new JLabel("Exporter: ");
		texporter.setBounds(10, 40, 100, 20);
		texporter.setHorizontalAlignment(JLabel.RIGHT);
		add(texporter);
		
		exporter = new JLabel(MetaData.getExporter());
		exporter.setBounds(120, 40, 260, 20);
		setSomeSets(exporter);
		add(exporter);
		
		tauthor = new JLabel("Author: ");
		tauthor.setBounds(10, 70, 100, 20);
		tauthor.setHorizontalAlignment(JLabel.RIGHT);
		add(tauthor);
		
		author = new JLabel(MetaData.getAuthor());
		author.setBounds(120, 70, 260, 20);
		setSomeSets(author);
		add(author);
		
		tcompany  = new JLabel("Company: ");
		tcompany.setBounds(10, 100, 100, 20);
		tcompany.setHorizontalAlignment(JLabel.RIGHT);
		add(tcompany);
		
		company = new JLabel(MetaData.getCompany());
		company.setBounds(120, 100, 260, 20);
		setSomeSets(company);
		add(company);
		
		tdescription = new JLabel("Description: ");
		tdescription.setBounds(10, 130, 100, 20);
		tdescription.setHorizontalAlignment(JLabel.RIGHT);
		add(tdescription);
		
		description = new JLabel(MetaData.getDescription());
		description.setBounds(120, 130, 260, 20);
		setSomeSets(description);
		add(description);
		
		tpmCreateDateTime = new JLabel("Created date: ");
		tpmCreateDateTime.setBounds(10, 160, 100, 20);
		tpmCreateDateTime.setHorizontalAlignment(JLabel.RIGHT);
		add(tpmCreateDateTime);
		
		pmCreateDateTime = new JLabel(MetaData.getPmCreateDateTime());
		pmCreateDateTime.setBounds(120, 160, 260, 20);
		setSomeSets(pmCreateDateTime);
		add(pmCreateDateTime);
		
		tpmLastModified = new JLabel("Last modified: ");
		tpmLastModified.setBounds(10, 190, 100, 20);
		tpmLastModified.setHorizontalAlignment(JLabel.RIGHT);
		add(tpmLastModified);
		
		pmLastModified = new JLabel(MetaData.getPmLastModified());
		pmLastModified.setBounds(120, 190, 260, 20);
		setSomeSets(pmLastModified);
		add(pmLastModified);
	}
	private void setSomeSets(JLabel label){
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
	}
}



























