package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import edu.duke.*;

public class VigenereBreaker {

	public String sliceString(String message, int start, int step) {
		// System.out.println(message + "\n");

		StringBuilder sb = new StringBuilder();

		for (int k = start; k < message.length(); k += step) {
			sb.append(message.charAt(k));
		}

		// System.out.println("starting index " + start + "\n" + sb + "\n");
		return sb.toString();
	}

	public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
		int[] key = new int[klength];

		for (int k = 0; k < klength; k++) {

			String line = sliceString(encrypted, k, klength);
			// System.out.println("Line made from SliceString is: " + line);
			CaesarCracker CC = new CaesarCracker(mostCommon);
			String decryptedLine = CC.decrypt(line);
			// System.out.println("Decrypted Line made from CC.decrypt is: " +
			// decryptedLine);
			int dkey = CC.getKey(line);
			// System.out.println("DKEY is: " + dkey);

			key[k] = dkey;
		}
	//	System.out.println(Arrays.toString(key)); // (key.toString()));
		return key;
	}

	public void breakVigenere(String fileName) {

		VigenereBreaker VB = new VigenereBreaker();

		String line = "";
		String lineComb = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				line.trim();

				lineComb += line + " ";
				// lineComb.trim();
			}
			lineComb.trim(); // whole file as a string

			// System.out.println("*************************************************************************");
			// System.out.println(lineComb);
			// System.out.println("*************************************************************************");
			//


			HashMap <String, HashSet<String> > mapOfDictionaries = new HashMap<String, HashSet<String> >(); 
			
		String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
			
			for (String s : langs){
				HashSet<String> dictionary = readDictionary(s+".txt");
				mapOfDictionaries.put(s, dictionary); 
				System.out.println("DONE WITH " + s + " DICTIONARY");
			}
			
			String decrypted = breakForAllLangs(lineComb, mapOfDictionaries);

			// System.out.println(decrypted);

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

	public HashSet<String> readDictionary(String fileName) {

		HashSet<String> set = new HashSet<String>();

		String line = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				set.add(line.trim().toLowerCase());
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
		//
		// for (String s : set){
		// System.out.println(s);
		// }
		//
		// Iterator<String> iter = set.iterator();
		// while (iter.hasNext()) {
		// System.out.println(iter.next());
		// }

		return set;
	}

	public int countWords(String message, HashSet<String> dictionary) {

		int counter = 0;

		String[] words = message.split("\\W+");

		for (int k = 0; k < words.length; k++) {
			words[k].toLowerCase();

			if (words[k].length() > 0 && !Character.isLetter(words[k].charAt(0))) {
				words[k] = words[k].substring(1, words[k].length());
			}
			if (words[k].length() > 1 && !Character.isLetter(words[k].charAt(words[k].length() - 2))) {
				words[k] = words[k].substring(0, words[k].length() - 2);
			}

			else if (words[k].length() > 0 && !Character.isLetter(words[k].charAt(words[k].length() - 1))) {
				words[k] = words[k].substring(0, words[k].length() - 1);
			}

			// for(String s : dictionary) {
			if (dictionary.contains(words[k].toLowerCase())) {
				counter++;
			}
			// else{
			// System.out.println(k);
			// System.out.println(words[k].toLowerCase());
			// }

			// System.out.println();
			// }

		}
//		System.out.println("Total words found: " + words.length + "    " +  "Total words matched " + counter);

		return counter;
	}

	public String breakForLanguage(String encrypted, HashSet<String> dictionary) {

		char maxChar;
		maxChar = mostCommonCharIn(dictionary);
		
		HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();

		for (int k = 1; k < 101; k++) {

			int[] keys = tryKeyLength(encrypted, k, maxChar);
			VigenereCipher VC = new VigenereCipher(keys);
			String decrypted = VC.decrypt(encrypted);
			int counter = countWords(decrypted, dictionary);

			myMap.put(k, counter);

		}

		int largestCountOfRealWords = Integer.MIN_VALUE;
		int keyOflargestCountOfRealWords = 0;
		for (HashMap.Entry<Integer, Integer> entry : myMap.entrySet()) {
			if (entry.getValue() > largestCountOfRealWords) {
				largestCountOfRealWords = entry.getValue();
				keyOflargestCountOfRealWords = entry.getKey();
			}
		}

		System.out.println("*************************************************************************");
		System.out.println("KEY LENGTH IS:     " + keyOflargestCountOfRealWords);
		int[] key = tryKeyLength(encrypted, keyOflargestCountOfRealWords, maxChar);
		System.out.println("\n" + "KEY IS:     ");
		System.out.println(Arrays.toString(key));

		VigenereCipher VC = new VigenereCipher(key);
		String decrypted = VC.decrypt(encrypted);
		System.out.println("Decrypted using keyOflargestCountOfRealWords:  " + "\n" + decrypted.substring(0, 100));

		int counter = countWords(decrypted, dictionary);
		System.out.println("Total words that matched: " + counter);
		System.out.println("*************************************************************************");

		return decrypted;
	}

	public char mostCommonCharIn(HashSet<String> dictionary) {

		HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();

		for (String word : dictionary) { // going through every word
				
			word.toLowerCase(); 
				
			for (int k = 0; k < word.length(); k++) { // going through every letter of each word
				
				if (!myMap.containsKey(word.charAt(k))) { // incrementing the letter's count										
					
					myMap.put(word.charAt(k), 1); // adding the letter if not in the map
					
				} else {
					int value = myMap.get(word.charAt(k));
					myMap.put(word.charAt(k), value +1);													 
				}
			}
		}

//		for (HashMap.Entry<Character, Integer> entry : myMap.entrySet()) { // looping through every entry in myMap
//			System.out.println(entry.getKey() + entry.getValue());
//		}
		
		int currval = 0;
		char maxChar = 'a';

		for (HashMap.Entry<Character, Integer> entry : myMap.entrySet()) { // looping through every entry in myMap
			if (entry.getValue() >= currval) {
				currval = entry.getValue();
				maxChar = entry.getKey();
			}
		}
		System.out.println("Most Common Character is:   " + maxChar);
		return maxChar;
	}
	
	
	public String breakForAllLangs(String encrypted, HashMap <String, HashSet<String> > languages){
	
		String decrypted = ""; 
		
		for (HashMap.Entry <String, HashSet<String> > entry : languages.entrySet()){  //go through every entry
			System.out.println("\n" + "For the Language:    " + entry.getKey() + "\n");
			decrypted = breakForLanguage(encrypted, entry.getValue()); 
		}
		return decrypted;
	}
	
	
	
	
	
}
	
	
	
	

	// public static void main(String args[]) {
	// VigenereBreaker VB = new VigenereBreaker();
	// // VB.sliceString("abcdefghijklm", 4, 5);
	// // VB.readDictionary("English.txt");
	// }