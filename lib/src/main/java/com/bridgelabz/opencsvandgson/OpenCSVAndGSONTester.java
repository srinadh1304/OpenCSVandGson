package com.bridgelabz.opencsvandgson;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVAndGSONTester {
	
	private static final String SAMPLE_CSV_FILE = "users.csv";
	private static final String SAMPLE_JSON_FILE = "users.json";
	
	public static void main(String[] args) {
		
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE));
			CsvToBeanBuilder<CSVUser> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVUser.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVUser> csvToBean = csvToBeanBuilder.build();
			List<CSVUser> csvUsers = csvToBean.parse();
			Gson gson = new Gson();
			String json = gson.toJson(csvUsers);
			FileWriter writer = new FileWriter(SAMPLE_JSON_FILE);
			writer.write(json);
			writer.close();
			
			BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_FILE));
			CSVUser[] usrObj = gson.fromJson(br, CSVUser[].class);
			List<CSVUser> csvUserList = Arrays.asList(usrObj);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
