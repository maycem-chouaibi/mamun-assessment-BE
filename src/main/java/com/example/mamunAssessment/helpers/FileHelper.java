package com.example.mamunAssessment.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;

// helper for manipulating files

public class FileHelper {

	// change file from string to arraylist
	public ArrayList<List<String>> fromatFileData(String  fileAsString) {
		List<String> lines = Arrays.asList(java.net.URLDecoder.decode(fileAsString, StandardCharsets.UTF_8).split("\n"));
		ArrayList<List<String>> fileContents = new ArrayList<>();
		lines.forEach(line -> fileContents.add(Arrays.asList(line.split(","))));
		return fileContents;
	}

	// return number of lines of a file
	public int nbLines(ArrayList<List<String>> file) {
		return file.size();
	}

	// return n lines from a file
	public List<List<String>> nLines(ArrayList<List<String>> file, int n) {
		return nbLines(file) > n ? file.subList(0, n): file;
	}

	// return n columns from file
	public List<List<String>> nColumns(List<List<String>> file, int n) {
		ArrayList<List<String>> columns = new ArrayList<>();
		file.forEach(line -> columns.add(line.size() > n ? line.subList(0, n) : line));

		return columns;
	}

	// write results as string for FE
	public String toString(List<List<String>> file) {
		String result = "";
		for(int i = 0; i < file.size(); i++) {
			result +=  "\t|\t";
			for(int j = 0; j < file.get(i).size(); j++) 
				result += file.get(i).get(j) + "\t|\t";
			result += "\n";
		}
		return result;
	}

	// write results to csv file
	public void toCSVFile(List<List<String>> file) throws IOException {
		File csv = new File("test.csv");
		FileWriter fw = new FileWriter(csv);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i = 0; i < file.size(); i++) {
			bw.write(StringUtils.join(file.get(i), ','));
			bw.newLine();
		}

        bw.close();
        fw.close();
	}
}