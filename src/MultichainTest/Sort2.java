package MultichainTest;

import java.util.*;

import Hex.Transfer;
import multichain.command.*;
import multichain.object.*;

public class Sort2 {
	private List<List<String>> records = new ArrayList<List<String>>();
	Sort2(){};

	public List<List<String>> getRecords() {
		return records;
	}
	public void setRecords(List<List<String>> records) {
		this.records = records;
	}
	Sort2(Query4 query){
		for(StreamKeyItem item : query.getItems()) {
			List<String> record = new ArrayList<String>();
			record.add(item.getKey());
			String value = Transfer.hexToString(item.getData());
			String[] data = value.split("/");
			for(int i=0;i<data.length;i++) {
				record.add(data[i]);
			}
			this.records.add(record);
		}
	}
	
	public List<List<String>> sortByTime(){
		Collections.sort(records, new Comparator<List<String>>() {
			public int compare(List<String> r1, List<String> r2) {
				long timestamp1 = Long.parseLong(r1.get(1));
				long timestamp2 = Long.parseLong(r2.get(1));
				if(timestamp1 > timestamp2) {
					return 1;
				}
				if(timestamp1 == timestamp2) {
					return 0;
				}
				return -1;
			}
		});
		return records;
	}
	
	public List<List<String>> sortByRef(){
		Collections.sort(records, new Comparator<List<String>>() {
			public int compare(List<String> r1, List<String> r2) {
				int ref1 = Integer.parseInt(r1.get(2));
				int ref2 = Integer.parseInt(r2.get(2));
				if(ref1 > ref2) {
					return 1;
				}
				if(ref1 == ref2) {
					return 0;
				}
				return -1;
			}
		});
		return records;
	}
	
	public List<List<String>> sortByUser(){
		Collections.sort(records, new Comparator<List<String>>() {
			public int compare(List<String> r1, List<String> r2) {
				int user1 = Integer.parseInt(r1.get(3));
				int user2 = Integer.parseInt(r2.get(3));
				if(user1 > user2) {
					return 1;
				}
				if(user1 == user2) {
					return 0;
				}
				return -1;
			}
		});
		return records;
	}
	
	public List<List<String>> sortByActivity(){
		Collections.sort(records, new Comparator<List<String>>() {
			public int compare(List<String> r1, List<String> r2) {
				String act1 = r1.get(4);
				String act2 = r2.get(4);
				if(act1.compareTo(act2) > 0) {
					return 1;
				}
				if(act1.compareTo(act2) == 0) {
					return 0;
				}
				return -1;
			}
		});
		return records;
	}
	
	public List<List<String>> sortByResource(){
		Collections.sort(records, new Comparator<List<String>>() {
			public int compare(List<String> r1, List<String> r2) {
				String resource1 = r1.get(5);
				String resource2 = r2.get(5);
				if(resource1.compareTo(resource2) > 0) {
					return 1;
				}
				if(resource1.compareTo(resource2) == 0) {
					return 0;
				}
				return -1;
			}
		});
		return records;
	}
	
	
}
