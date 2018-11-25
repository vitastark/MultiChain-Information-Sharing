package MultichainTest;

import java.util.*;

public class Sort {
	public static List<List<String>> sortByTime(List<List<String>> records){
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
	
	public static List<List<String>> sortByRef(List<List<String>> records){
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
	
	public static List<List<String>> sortByUser(List<List<String>> records){
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
	
	public static List<List<String>> sortByActivity(List<List<String>> records){
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
	
	public static List<List<String>> sortByResource(List<List<String>> records){
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
