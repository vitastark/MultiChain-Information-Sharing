package MultichainTest;

import Assemble.*;
import Hex.*;
import ReadFile.*;
import multichain.command.*;
import multichain.object.*;
import java.util.*;

public class AddData2 {
	static MultiChainCommand multiChainCommand;
	public AddData2() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getAddress() throws MultichainException {
		String address = multiChainCommand.getAddressCommand().getAddresses().get(0);
		return address;
	}
	
	public static List<String> getPeerAddresses() throws MultichainException{
		List<Permission> permissions = multiChainCommand.getGrantCommand().listPermissions();
		List<String> addresses = new ArrayList<String>();
		for(Permission permission : permissions) {
			if(permission.getType().equals("connect")) {
				addresses.add(permission.getAddress());
			}
		}
		return addresses;
	}
	
	public static String getMainAddress() throws MultichainException{
		List<Permission> permissions = multiChainCommand.getGrantCommand().listPermissions();
		String mainAddress = "";
		for(Permission permission : permissions) {
			if(permission.getType().equals("admin")) {
				mainAddress = permission.getAddress();
			}
		}
		return mainAddress;
	}
	
	public static void createStream(String streamName) {
		String result = "";
		try {
			result = multiChainCommand.getStreamCommand().create(streamName);
		} 
		catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == null || "".equals(result)) {
			System.err.println("testcreate - result is empty");
		} else {
			System.out.println("Result :");
			System.out.println(result);
		}
	}
	
	public static void subscribe(String streamName) {
		try {

			multiChainCommand.getStreamCommand().subscribe(streamName);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result : subscribe done !");
	}
	
	public static void publish(String streamName, String key, String value) {
		String result = "";
		try {
			result = multiChainCommand.getStreamCommand().publish(streamName, key, value);
		}
		catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == null || "".equals(result)) {
			System.err.println("testpublish - result is empty");
		} else {
			System.out.println("Result :");
			System.out.println(result);
		}		
	}
		
	
	public static void test() throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();
		System.out.println("Please enter file route:");
		Scanner scan = new Scanner(System.in);		
		data = ReadFile.readData(scan.next());
		data = AssembleData.assembleNaive(data);
		
		List<String> peeraddresses = getPeerAddresses();
		
		List<Stream> streams = multiChainCommand.getStreamCommand().listStreams("*");
		List<String> streamnames = new ArrayList<String>();
		for(Stream stream : streams) {
			streamnames.add(stream.getName());
		}
		if(streamnames.contains("stream1") == false) {
			createStream("stream1");
			for(String peeraddress : peeraddresses) {
				multiChainCommand.getGrantCommand().grantWrite(peeraddress, "stream1");
			}
		}
		//subscribe("stream1");
		long count = 1;
		
		for(List<String> record : data) {
			publish("stream1", record.get(0), Transfer.strToHex(record.get(1)));
			System.out.println("record "+count+"has been published");
			count++;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Please enter username(rpc):");
		Scanner scan1 = new Scanner(System.in);
		String username = scan1.next();
		System.out.println("Please enter password:");
		Scanner scan2 = new Scanner(System.in);
		String password = scan1.next();
		multiChainCommand = new MultiChainCommand("localhost", username, "multichainrpc", password);
		
		long starttime = System.currentTimeMillis();
		test();
		long endtime = System.currentTimeMillis();
		System.out.println("Total time is: "+ (endtime-starttime) + "ms");
		

	}
	
	

}
