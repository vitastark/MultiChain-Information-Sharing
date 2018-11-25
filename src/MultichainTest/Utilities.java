package MultichainTest;

import java.util.*;
import multichain.command.*;
import multichain.object.Stream;
import multichain.object.StreamKeyItem;
import ReadFile.*;
import Assemble.*;
import Hex.*;

public class Utilities {
	public static void printRecords(List<List<String>> records) {
		for(List<String> record : records) {
			if(record == null) {
				continue;
			}
			else {
				System.out.println("id: "+record.get(0));
				System.out.println("timestamp: "+record.get(1));
				System.out.println("ref: "+record.get(2));
				System.out.println("user: "+record.get(3));
				System.out.println("activity: "+record.get(4));
				System.out.println("location: "+record.get(5));
				System.out.println();
				System.out.println();
			}	
		}
	}
	
	public static void printSingleRecord(List<String> record) {
		System.out.println("id: "+record.get(0));
		System.out.println("timestamp: "+record.get(1));
		System.out.println("ref: "+record.get(2));
		System.out.println("user: "+record.get(3));
		System.out.println("activity: "+record.get(4));
		System.out.println("location: "+record.get(5));
		System.out.println();
		System.out.println();
	}
	
	public static void printSingleRecordNaive(List<String> record) {
		System.out.println("id: "+record.get(0));
		String[] data = record.get(1).split("/");
		System.out.println("timestamp: "+data[0]);
		System.out.println("ref: "+data[1]);
		System.out.println("user: "+data[2]);
		System.out.println("activity: "+data[3]);
		System.out.println("location: "+data[4]);
		System.out.println();
		System.out.println();
	}
	
	public static void printRecordsNaive(List<List<String>> records) {
		for(List<String> record : records) {
			printSingleRecordNaive(record);
		}
	}
	
	public static void printItem(StreamKeyItem item) {
		String key = item.getKey();
		String value = Transfer.hexToString(item.getData());
		String[] id = key.split("\\.");
		String[] data = value.split("/");
		System.out.print(data[0]+"\t");
		System.out.print(id[0]+"\t");
		System.out.print(id[1]+"\t");
		System.out.print(data[1]+"\t");
		System.out.print(data[2]+"\t");
		System.out.print(data[3]+"\t");
		System.out.println(data[4]);
	}
	
	public static void printItems(List<StreamKeyItem> items) {
		for(StreamKeyItem item : items) {
			printItem(item);
		}
	}

}
