package Assemble;

import java.util.*;

public class AssembleData {
	public static List<List<String>> assemble(List<List<String>> data){
		List<List<String>> newdata = new ArrayList<List<String>>();
		for(List<String> record : data) {
			List<String> newrecord = new ArrayList<String>();
			String time = record.get(0);
			String trainingset = record.get(1);
			String index = record.get(2);
			String id = trainingset+"."+index;
			String ud1 = record.get(3);
			String ud2 = record.get(4);
			String action = record.get(5);
			String location = record.get(6);
			newrecord.add(0, id);
			newrecord.add(1, time);
			newrecord.add(2, ud1);
			newrecord.add(3, ud2);
			newrecord.add(4, action);
			newrecord.add(5, location);
			newdata.add(newrecord);
		}
		return newdata;
	}
	
	public static List<List<String>> assembleNaive(List<List<String>> data){
		List<List<String>> newdata = new ArrayList<List<String>>();
		for(List<String> record : data) {
			List<String> newrecord = new ArrayList<String>();
			String time = record.get(0);
			String trainingset = record.get(1);
			String index = record.get(2);
			String id = trainingset+"."+index;
			String ud1 = record.get(3);
			String ud2 = record.get(4);
			String action = record.get(5);
			String location = record.get(6);
			String value = time+"/"+ud1+"/"+ud2+"/"+action+"/"+location;
			newrecord.add(0, id);
			newrecord.add(1, value);
			newdata.add(newrecord);
		}
		return newdata;
	}

}
