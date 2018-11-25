package Assemble;

import ReadFile.*;
import java.util.*;


public class AssembleDataTest {
	public static void main(String[] args) throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();
		data = ReadFile.readData("resources/sample1.txt");
		data = AssembleData.assembleNaive(data);
		ReadFile.printData(data);
	}
}
