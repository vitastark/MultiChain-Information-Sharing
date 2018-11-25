package MultichainTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Hex.Transfer;
import multichain.command.*;
import multichain.object.*;

public class Query4 {
	private List<Stream> streams = new ArrayList<Stream>();
	private List<StreamKeyItem> items = new ArrayList<StreamKeyItem>();
	static MultiChainCommand multiChainCommand;
	public List<Stream> getStreams() {
		return streams;
	}
	public void setStreams(List<Stream> streams) {
		this.streams = streams;
	}
	public List<StreamKeyItem> getItems() {
		return items;
	}
	public void setItems(List<StreamKeyItem> items) {
		this.items = items;
	}
	public Query4() throws MultichainException{
		this.streams = multiChainCommand.getStreamCommand().listStreams("*");		
		List<String> ids = new ArrayList<String>();
		for(Stream stream : streams) {
			List<StreamKeyItem> items = multiChainCommand.getStreamCommand().listStreamKeyItems(stream.getName(), "*", false, 100000);
			for(StreamKeyItem item : items) {
				this.items.add(item);
			}
		}	
	}
	
	public Query4 select(Conditions conds) throws MultichainException{

		
		if(conds.getIndex() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = item.getKey();
				String[] data = value.split("\\.");
				if(data[1].equals(conds.getIndex())) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		if(conds.getLowerbound()!="" && conds.getUpperbound()!="") {
			long lowerbound = Long.parseLong(conds.getLowerbound());
			long upperbound = Long.parseLong(conds.getUpperbound()); 
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = Transfer.hexToString(item.getData());
				String[] data = value.split("/");
				long timestamp = Long.parseLong(data[0]);
				if(timestamp >= lowerbound && timestamp <= upperbound) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		if(conds.getResource() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = Transfer.hexToString(item.getData());
				String[] data = value.split("/");
				if(data[4].equals(conds.getResource())) {
					new_items.add(item);
				}
			}
			this.items = new_items;		
		}
		
		if(conds.getRef() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = Transfer.hexToString(item.getData());
				String[] data = value.split("/");
				if(data[1].equals(conds.getRef())) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		if(conds.getUser() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = Transfer.hexToString(item.getData());
				String[] data = value.split("/");
				if(data[2].equals(conds.getUser())) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		if(conds.getActivity() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = Transfer.hexToString(item.getData());
				String[] data = value.split("/");
				if(data[3].equals(conds.getActivity())) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		if(conds.getNode() != "") {
			List<StreamKeyItem> new_items = new ArrayList<StreamKeyItem>();
			for(StreamKeyItem item : this.items) {
				String value = item.getKey();
				String[] data = value.split("\\.");
				if(data[0].equals(conds.getNode())) {
					new_items.add(item);
				}
			}
			this.items = new_items;
		}
		
		return this;
	}
	
	public static Conditions construct(String[] args) {
		Conditions conds = new Conditions();
		for(int i=2; i<args.length; i++) {
			String[] temp = args[i].split("=");
			String value;
			if(temp.length == 1) {
				continue;
			}
			else {
				value = temp[1];
			}
			if(i-2==0) conds.setLowerbound(value);
			if(i-2==1) conds.setUpperbound(value);
			if(i-2==2) conds.setNode(value);
			if(i-2==3) conds.setIndex(value);
			if(i-2==4) conds.setRef(value);
			if(i-2==5) conds.setUser(value);
			if(i-2==6) conds.setActivity(value);
			if(i-2==7) conds.setResource(value);
			
		}
		
		return conds;
	}
	
	public static void main(String[] args) throws MultichainException {
		multiChainCommand = new MultiChainCommand("localhost", args[0], "multichainrpc", args[1]);
		
		long starttime = System.currentTimeMillis();
		Query4 query = new Query4();
		long endtime = System.currentTimeMillis();
		System.out.println("Constructor Query uses: "+ (endtime-starttime) + "ms");
		
		starttime = System.currentTimeMillis();
		Conditions conds = construct(args);
		endtime = System.currentTimeMillis();
		System.out.println("constructor Conditions uses: "+ (endtime-starttime) + "ms");
		
		conds.showconds();
	
		starttime = System.currentTimeMillis();
		query.select(conds);
		endtime = System.currentTimeMillis();
		System.out.println("select fuction uses: "+ (endtime-starttime) + "ms");
		
		starttime = System.currentTimeMillis();
		System.out.println(query.getItems().size() +" items are found!");
		endtime = System.currentTimeMillis();
		
		
		
		Utilities.printItems(query.getItems());
	}
	
}
