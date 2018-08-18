package aziz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestCaesarCipherTwo {

	public static String halfOfString(String message, int start) {

		StringBuilder halfString = new StringBuilder("");

		for (int k = start; k < message.length(); k++) {
			if ((k % 2) == (start % 2)) {
				halfString.append(message.charAt(k));
			}
		}
		// System.out.println(halfString);
		return halfString.toString();
	}

	public static int[] countLetters(String message) {

		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int k = 0; k < message.length(); k++) {
			char ch = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(ch);
			if (dex != -1) {
				counts[dex]++;
			}
		}
		return counts;
	}

	public static int maxIndex(int[] vals) {
		int maxDex = 0;
		for (int k = 0; k < vals.length; k++) {
			if (vals[k] > vals[maxDex]) {
				maxDex = k;
			}
		}
		// System.out.println(maxDex);
		return maxDex;
	}
	
	
	public static int getKey(String s){
		
		int[] letterFreqsOfS = countLetters(s);
		int indexOfLargestLetterFreq = maxIndex(letterFreqsOfS);
		
		int dkey = indexOfLargestLetterFreq -4;
		if (indexOfLargestLetterFreq <4){
			dkey = 26 - (4-indexOfLargestLetterFreq);
		}	
		return dkey;
	}	
		

	public static void simpleTests(String fileName) {
		
		CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
		
	
		// This will reference one line at a time
		String line = null;
		String result = "";
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//while ((line = bufferedReader.readLine()) != null) {
			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){	
				//	System.out.println(line); //what does your function do? we need to fill the while loop - do it. it is long


				line = line.trim();
				result += line;
				
				if(!line.isEmpty()){
					
					String encrypted = cc.encrypt(line);
					System.out.println("Encrypted Line: " + encrypted);
					String decrypted = cc.decrypt(encrypted);
					System.out.println("Decrypted Line: " + decrypted);
					
					
				}
	} breakCaesarCipher(result); 
	
	
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
	
	
	public static String breakCaesarCipher (String input){
		String firstHalf = halfOfString(input, 0);
		String secondHalf = halfOfString(input,1);
	//	System.out.println(firstHalf);
	//	System.out.println(secondHalf);
		
		int keyOne = getKey(firstHalf);
		int keyTwo = getKey(secondHalf);
		
		System.out.println("First Key is: "+ keyOne);
		System.out.println("Second Key is: "+ keyTwo);
		
		CaesarCipherTwo cc = new CaesarCipherTwo(keyOne, keyTwo);
		String decrypted = cc.decrypt(input);
		System.out.println("Decypted w/ breakCC: " + decrypted);
		return decrypted;
	}
	
	
	public static void main (String args[]){
	//	simpleTests("twoKeysPractise.txt");
		breakCaesarCipher("mysteryTwoKeyQuiz.txt");
	}
}



