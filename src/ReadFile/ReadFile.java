package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ReadFile {
	public static List<List<String>> readData(String path) throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();
		try {
			File filename = new File(path);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			while(line!=null) {
				List<String> record = new ArrayList<String>();
				line = br.readLine();
				if(line == null || line.equals("")) {
					break;
				}
				String[] arr = line.split("\\s+");
				for(String ss : arr) {
					record.add(ss);
				}
				data.add(record);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void printRecord(List<String> record) {
		for(String attribute : record) {
			System.out.println(attribute);
		}
	}
	
	public static void printData(List<List<String>> data) {
		for(List<String> record : data) {
			printRecord(record);
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<List<String>> data = readData("../resources/sample1.txt");
		printData(data);
		
	}

}
