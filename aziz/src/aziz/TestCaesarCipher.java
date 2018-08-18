package aziz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestCaesarCipher {

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
	
public static String breakCaesarCipher(String input){
		
		int[] letterFreqsOfInput = countLetters(input);
		int indexOfLargestLetterFreq = maxIndex(letterFreqsOfInput);
		
		int dkey = indexOfLargestLetterFreq -4;
		if (indexOfLargestLetterFreq <4){
			dkey = 26 - (4-indexOfLargestLetterFreq);
		}
		
		CaesarCipherNew cc = new CaesarCipherNew(dkey);
		String decrypted = cc.decrypt(input);
		System.out.println(decrypted);
		return decrypted;
	}


	public static void simpleTests(String fileName) {
		
		CaesarCipherNew cc = new CaesarCipherNew(18);
		
		
		
		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				// System.out.println(line); //what does your function do? we
				// need to fill the while loop - do it. it is long

				line = line.trim();
				// System.out.println(line);
				if (!line.isEmpty()) {
					String encrypt = cc.encrypt(line);
					System.out.println("Encrypted String is: " + encrypt);
					String decrypt = cc.decrypt(encrypt);
					System.out.println("Decrypted String is: " + decrypt);
					String brokeCC = breakCaesarCipher(encrypt);
					System.out.println("breakCasearCipher: " +brokeCC);
				}

			
			}	// Always close files.
				bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	
	
	public static void main(String args[]){
		
			simpleTests("smallHamlet.txt");
		
	}

}


