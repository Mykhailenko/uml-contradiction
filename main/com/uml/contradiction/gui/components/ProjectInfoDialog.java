package com.uml.contradiction.gui.components;

import javax.swing.JDialog;
import javax.swing.JLabel;

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
		tprojectName.setBounds(10, 10, 110, 20);
		add(tprojectName);
		
		projectName = new JLabel(MetaData.getProjectName());
		projectName.setBounds(120, 10, 270, 20);
		add(projectName);
		
		texporter = new JLabel("Exporter: ");
		texporter.setBounds(10, 40, 110, 20);
		add(texporter);
		
		exporter = new JLabel(MetaData.getExporter());
		exporter.setBounds(120, 40, 270, 20);
		add(exporter);
		
		tauthor = new JLabel("Author: ");
		tauthor.setBounds(10, 70, 110, 20);
		add(tauthor);
		
		author = new JLabel(MetaData.getAuthor());
		author.setBounds(120, 70, 270, 20);
		add(author);
		
		tcompany  = new JLabel("Company: ");
		tcompany.setBounds(10, 100, 110, 20);
		add(tcompany);
		
		company = new JLabel(MetaData.getCompany());
		company.setBounds(120, 100, 270, 20);
		add(company);
		
		tdescription = new JLabel("Description: ");
		tdescription.setBounds(10, 130, 110, 20);
		add(tdescription);
		
		description = new JLabel(MetaData.getDescription());
		description.setBounds(120, 130, 270, 20);
		add(description);
		
		tpmCreateDateTime = new JLabel("Created date: ");
		tpmCreateDateTime.setBounds(10, 160, 110, 20);
		add(tpmCreateDateTime);
		
		pmCreateDateTime = new JLabel(MetaData.getPmCreateDateTime());
		pmCreateDateTime.setBounds(120, 160, 270, 20);
		add(pmCreateDateTime);
		
		tpmLastModified = new JLabel("Last modified: ");
		tpmLastModified.setBounds(10, 190, 110, 20);
		add(tpmLastModified);
		
		pmLastModified = new JLabel(MetaData.getPmLastModified());
		pmLastModified.setBounds(120, 190, 270, 20);
		add(pmLastModified);
	}
}



























