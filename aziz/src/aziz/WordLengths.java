//package aziz;
//
//import java.io.*;
//
//public class WordLengths {
//
//	public static void main(String[] args) {
//		readFile();
//	}
//
//	public void countWordLengths(String[] resource, int[] counts) {
//
//
//	}
//
//
//	static String [] resource = {"smallHamlet.txt"};
//
//	public static void readFile() {
//
//
//		for (int k = 0; k < resource.length; k++){
//
//
//			// The name of the file to open.
//			String fileName = resource[k];
//
//			// This will reference one line at a time
//			String line = null;
//
//			try {
//				// FileReader reads text files in the default encoding.
//				FileReader fileReader = new FileReader(fileName);
//
//				// Always wrap FileReader in BufferedReader.
//				BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//				while ((line = bufferedReader.readLine()) != null) {
//					System.out.println(line);
//				}
//
//				// Always close files.
//				bufferedReader.close();
//			} catch (FileNotFoundException ex) {
//				System.out.println("Unable to open file '" + fileName + "'");
//			} catch (IOException ex) {
//				System.out.println("Error reading file '" + fileName + "'");
//				// Or we could just do this:
//				// ex.printStackTrace();
//			}
//		}
//	}
//}














package aziz;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WordLengths {


	public static void countWordLengths(String fileName, int[] counts) {

		// This will reference one line at a time
		String line = null;
		
		
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//while ((line = bufferedReader.readLine()) != null) {
			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){	
				//System.out.println(line); //what does your function do? we need to fill the while loop - do it. it is long
				
				
			//	System.out.println("before replacing numbers: " + line); //what does your function do? we need to fill the while loop - do it. it is long
				line = line.replaceAll("\\d", "");
			//	System.out.println("after replacing: " + line);
				line = line.trim();
				
			//	System.out.println("after trimming: " + line);
				if(!line.isEmpty()){
				
				String[] words = line.split("\\s+");
			
				System.out.println(Arrays.toString(words));
			//	System.out.println(words[0].length());
				for (int k=0; k < words.length; k++){
					
					String str = words[k].trim();
					
					
					
					int l = str.length() ;
					
					char first = str.charAt(0);
					char last = str.charAt(l-1);
				//	System.out.println(first );
				//	System.out.println(last );
					
					if (Character.isAlphabetic(first) != true){
						l --;
					}
					if (Character.isAlphabetic(last) == false){
						l --;
					}
					if(l >= 30){
						counts[31]++;
					} else {
					
					counts[l]++;
					System.out.println(Arrays.toString(counts));
				}
				}	
				}
				
				
				
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
	
	
	public static int indexOfMax(int[] values){
		int currentMax = Integer.MIN_VALUE;
		int indexOfMax=0; 
		for (int k=0; k < values.length; k++){
			if (values[k] > currentMax){
				currentMax = values[k];
				indexOfMax = k;
			} 
		}
		System.out.println(indexOfMax);
		return indexOfMax;
		
	} 
	
	
	
	

	public static void main(String[] args) {
		int[] count  = new int[31]; 
		countWordLengths ("manyWords.txt", count);
		indexOfMax(count);
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		list.add(6);
		
		
//		String hello = "hello";
//		System.out.println(hello.charAt(0));
//		char ch = hello.charAt(0);
//		if (Character.isLetter(hello.charAt(4)) == false) {
//			System.out.println("its not a character");
//		} else {
//			System.out.println("yes it is a character");
//		}
//		
//		String str = "This is a simple sentence";
//		String[] strgs = str.split(" ");
//		System.out.println(Arrays.toString(strgs));
	}
}
