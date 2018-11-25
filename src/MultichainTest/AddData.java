package MultichainTest;

import Assemble.*;
import Hex.*;
import ReadFile.*;
import multichain.command.*;
import multichain.object.*;
import java.util.*;

public class AddData {
	static MultiChainCommand multiChainCommand;
	public AddData() {
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
		} 		
	}
		
	
	public static void test() throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();
		System.out.println("Please enter file route:");
		Scanner scan = new Scanner(System.in);		
		data = ReadFile.readData(scan.next());
		data = AssembleData.assemble(data);
		
		List<String> peeraddresses = getPeerAddresses();
		
		List<Stream> streams = multiChainCommand.getStreamCommand().listStreams("*");
		List<String> streamnames = new ArrayList<String>();
		for(Stream stream : streams) {
			streamnames.add(stream.getName());
		}
		
		for(int i=1;i<=10;i++) {
			if(streamnames.contains("stream"+i) == false) {
				createStream("stream"+i);
				for(String peeraddress : peeraddresses) {
					multiChainCommand.getGrantCommand().grantWrite(peeraddress, "stream"+i);
				}			
			}
			subscribe("stream"+i);	
		}
		long count = 1;
		for(List<String> record : data) {
			for(int i = 1;i < record.size(); i++) {
				publish("stream"+i, record.get(0), Transfer.strToHex(record.get(i)));
				publish("stream"+(i+5), record.get(i), Transfer.strToHex(record.get(0)));
				
			}
			System.out.println("Record "+count+ " published");
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

//server rpcpassword = E8GvkU8vnVgnUoXpjMHEfvpJzSnEu9qaMtwUHB2RtWAY
//server rpc-port = 5788


//local rpcpassword = 9ocvCdqYYNUafX1xXsjnZeUHKCQkCY8ndUVTEs4uwbqt
//local rpc-port = 7404



