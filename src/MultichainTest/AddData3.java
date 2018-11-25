/**
 * @author Haiyang Sun
 * @version: v3.0
 * @className: AddData3
 * @packageName: MultichainTest
 * 
 * This class is the third AddData class, this class mainly package
 * some of StringCommands methods in MultiChainJava.
 */
package MultichainTest;

import Assemble.*;
import Hex.*;
import ReadFile.*;
import multichain.command.*;
import multichain.object.*;
import java.util.*;

public class AddData3 {
	static MultiChainCommand multiChainCommand;

	public AddData3() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * getAddress
	 * return string adress
	 * use to get self - address
	 * @return
	 * @throws MultichainException
	 */
	public static String getAddress() throws MultichainException {
		String address = multiChainCommand.getAddressCommand().getAddresses().get(0);
		return address;
	}
	
	/**
	 * getPeerAddresses
	 * use to get peer addresses
	 * @return
	 * @throws MultichainException
	 */
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
	
	
	/**
	 * createStream
	 * Pack the create function of MultiChainJava, try to create a stream
using the streamName.
	 * Augments: 1. streamName, function will use this value as stream's name.
	 * @param streamName
	 */
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
	
	/**
	 * subscribe
	 * Pack the subscribe function of MultiChainJava, to subscribe a stream.
	 * @param streamName
	 * @throws MultichainException
	 * @throws InterruptedException
	 */
	public static void subscribe(String streamName) throws MultichainException, InterruptedException {
		try {
			multiChainCommand.getStreamCommand().subscribe(streamName);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Begin to subscribe "+streamName);
	}
	
	/**
	 * publish
	 * Pack the publish function of MultiChainJava, to publish a key - value data on a stream
	 * Augments: 1. streamName, as a stream identifier.
	 * 			 2. key, string type, dataset number + index.
	 * 			 3. value, other properties, need to be transfered to hexadecimal.
	 * @param streamName
	 * @param key
	 * @param value
	 */
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
		
	
	public static void test(String route, String nodeId) throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();				
		data = ReadFile.readData(route);
		data = AssembleData.assembleNaive(data);
		
		List<String> peeraddresses = getPeerAddresses();
		
		List<Stream> streams = multiChainCommand.getStreamCommand().listStreams("*");
		List<String> streamnames = new ArrayList<String>();
		for(Stream stream : streams) {
			streamnames.add(stream.getName());
		}
		//int streamnumber = streamnames.size();
		//createStream("stream"+streamnumber);
		
		long count = 1;
		
		for(List<String> record : data) {
			publish("stream"+nodeId, record.get(0), Transfer.strToHex(record.get(1)));
			System.out.println("record "+count+" has been published");
			count++;
		}
		
		streams = multiChainCommand.getStreamCommand().listStreams("*");

		
	}
	
	public static void main(String[] args) throws Exception {
		multiChainCommand = new MultiChainCommand("localhost", args[0], "multichainrpc", args[1]);
		
		long starttime = System.currentTimeMillis();
		test(args[2], args[3]);
		long endtime = System.currentTimeMillis();
		System.out.println("Total time is: "+ (endtime-starttime) + "ms");
		

	}
	
	

}
