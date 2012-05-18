package com.uml.contradiction.model;

public class MetaData {
	private static String projectName;
	private static String exporter;
	private static String author;
	private static String company;
	private static String description;
	private static String pmCreateDateTime;
	private static String pmLastModified;

	public static void clear() {
		projectName = null;
		exporter = null;
		author = null;
		company = null;
		description = null;
		pmCreateDateTime = null;
		pmLastModified = null;
	}

	public static String getProjectName() {
		return projectName;
	}

	public static void setProjectName(String projectName) {
		MetaData.projectName = projectName;
	}

	public static String getExporter() {
		return exporter;
	}

	public static void setExporter(String exporter) {
		MetaData.exporter = exporter;
	}

	public static String getAuthor() {
		return author;
	}

	public static void setAuthor(String author) {
		MetaData.author = author;
	}

	public static String getCompany() {
		return company;
	}

	public static void setCompany(String company) {
		MetaData.company = company;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		MetaData.description = description;
	}

	public static String getPmCreateDateTime() {
		return pmCreateDateTime;
	}

	public static void setPmCreateDateTime(String pmCreateDateTime) {
		MetaData.pmCreateDateTime = pmCreateDateTime;
	}

	public static String getPmLastModified() {
		return pmLastModified;
	}

	public static void setPmLastModified(String pmLastModified) {
		MetaData.pmLastModified = pmLastModified;
	}

	public static void printMetaData() {
		System.out.println("MetaData: , projectName= " + projectName
				+ ", exporter= " + exporter + ", author= " + author
				+ ", company= " + company + ", description= " + description
				+ ", pmCreateDateTime= " + pmCreateDateTime
				+ ", pmLastModified= " + pmLastModified);
	}
}
