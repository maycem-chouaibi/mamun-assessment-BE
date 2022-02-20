package com.example.mamunAssessment.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mamunAssessment.helpers.FileHelper;
import com.example.mamunAssessment.model.FormData;

@RestController
@RequestMapping("/")
public class IndexController {

	@PostMapping("/")
	public HashMap<String, String> handleFileUpload(@RequestBody(required = false) FormData body) throws IOException {
		HashMap<String, String> returnResult = new HashMap<String, String>();
		// If values null, return error for validation
		if(body.getCollation() == null|| body.getFile1() == null || body.getFile2() == null) {
			returnResult.put("error", "Collation and files are required");
			return returnResult;
		}
		FileHelper fileHelper = new FileHelper();
		// Turn string file into arraylist
		ArrayList<List<String>> file1 = fileHelper.fromatFileData(body.getFile1());
		ArrayList<List<String>> file2 = fileHelper.fromatFileData(body.getFile2());

		// validation for nb lines to process
		if( body.getNbLines() > 100) {
			returnResult.put("error", "Number of lines to process must not exceed 100.");
			return returnResult;
		}

		// get 1st column of n lines from both files
		List<List<String>> column1 = fileHelper.nColumns(fileHelper.nLines(file1, body.getNbLines()), 1);
		List<List<String>> column2 = fileHelper.nColumns(fileHelper.nLines(file2, body.getNbLines()), 1);
		// reverse file2 results
		Collections.reverse(column2);

		ArrayList<List<String>> results = new ArrayList<List<String>>();
		int resultSize = 0;
		if(body.getCollation().equals("Normal"))
		{
			// if collation is normal, get smallest section size
			resultSize = Integer.min(column1.size(), column2.size());
		}
		else {
			// if collation is full, get biggest section size
			resultSize = Integer.max(column1.size(), column2.size());
		}

		for(int i = 0; i < resultSize; i++) {
			List<String> temp = new ArrayList<String>();
			if(i < column1.size())
				temp.addAll(column1.get(i));
			if(i < column2.size())
				temp.addAll(column2.get(i));
			results.add(temp);
		}

		if(body.isDownload()) {
			fileHelper.toCSVFile(results);
			returnResult.put("results", "Your file was downloaded successfully!");
			return returnResult;
		}

		returnResult.put("results", fileHelper.toString(results));
		return returnResult;
	}

}
