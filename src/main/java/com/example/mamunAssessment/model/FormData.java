package com.example.mamunAssessment.model;

public class FormData {	

	String file1;
	String file2;
	String collationDropdown;
	int nbLinesInput;
	boolean downloadCB;
	
	public FormData(String file1, String file2, String collationDropdown, int nbLinesInput, boolean downloadCB) {
		super();
		this.file1 = file1;
		this.file2 = file2;
		this.collationDropdown = collationDropdown;
		this.nbLinesInput = nbLinesInput;
		this.downloadCB = downloadCB;
	}
	
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public String getCollation() {
		return collationDropdown;
	}
	public void setCollation(String collation) {
		this.collationDropdown = collation;
	}
	public int getNbLines() {
		return nbLinesInput;
	}
	public void setNbLines(int nbLines) {
		this.nbLinesInput = nbLines;
	}
	public boolean isDownload() {
		return downloadCB;
	}
	public void setDownload(boolean download) {
		this.downloadCB = download;
	}
	
	}
