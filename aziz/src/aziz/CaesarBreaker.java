package aziz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CaesarBreaker {

String encrypted = ""; 	
	
	public static int[] countLetters(String message){
		
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int k=0; k < message.length(); k++){
			char ch = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(ch);
			if (dex != -1){
				counts[dex]++;
			}
		}
		return counts;
	}
	 
	public static String decrypt (String encrpyted){
		CaesarCipher cc = new CaesarCipher();
		int[] freqs = countLetters(encrpyted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex -4;
		if (maxDex <4){
			dkey = 26 - (4-maxDex);
		}
		return cc.encrypt(encrpyted, 26- dkey);
	}
	
	public static int maxIndex (int[] vals){
		int maxDex = 0 ;
		for (int k=0; k < vals.length; k++){
			if (vals[k] > vals[maxDex]){
				maxDex = k ;
			}
		}
		//System.out.println(maxDex);
		return maxDex;
	}
	
	public static String testDecrypt(String encrypted){
		
		String decrypted =  decrypt(encrypted);
		System.out.println(decrypted);
		return decrypted;
	}
	
	public static String halfOfString (String message, int start){
		
		StringBuilder halfString = new StringBuilder ("");
		
		for (int k = start; k < message.length(); k++){
			if((k % 2) == (start %2)){
				halfString.append(message.charAt(k));
			}
		}
		//System.out.println(halfString);
		return halfString.toString();
	}
	 
	
	public static int getKey(String s){
		
		int[] letterFreqsOfS = countLetters(s);
		int indexOfLargestLetterFreq = maxIndex(letterFreqsOfS);
		
		int dkey = indexOfLargestLetterFreq -4;
		if (indexOfLargestLetterFreq <4){
			dkey = 26 - (4-indexOfLargestLetterFreq);
		}
			
		return dkey;
		
		//return maxIndex(countLetters(s));
	}
	
	public static String decryptTwoKeys(String encrypted){
		String firstHalf = halfOfString(encrypted, 0);
		String secondHalf = halfOfString(encrypted,1);
		System.out.println(firstHalf);
		System.out.println(secondHalf);
		
		int keyOne = getKey(firstHalf);
		int keyTwo = getKey(secondHalf);
		
		System.out.println("First Key is: "+ keyOne);
		System.out.println("Second Key is: "+ keyTwo);
		
	//	CaesarCipher cc = new CaesarCipher();
		String originalMessage = CaesarCipher.encryptTwoKeys(encrypted, 26- keyOne, 26- keyTwo);
		System.out.println(originalMessage);
		return originalMessage;
	}
	
	
	
	public static void decryptTwoKeysFile(String fileName){

		// This will reference one line at a time
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//while ((line = bufferedReader.readLine()) != null) {
			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){	
				//	System.out.println(line); //what does your function do? we need to fill the while loop - do it. it is long


				line = line.trim();
				if(!line.isEmpty()){

				String firstHalf = halfOfString(line, 0);
				String secondHalf = halfOfString(line,1);
				System.out.println(firstHalf);
				System.out.println(secondHalf);

				int keyOne = getKey(firstHalf);
				int keyTwo = getKey(secondHalf);

				System.out.println("First Key is: "+ keyOne);
				System.out.println("Second Key is: "+ keyTwo);

				//	CaesarCipher cc = new CaesarCipher();
				String originalMessage = CaesarCipher.encryptTwoKeys(line, 26- keyOne, 26- keyTwo);
				System.out.println(originalMessage);
				//return originalMessage;
			}
			} 
			
		bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
		
		
		public static String combineFile(String fileName){
			
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
					
					result += line;
				//	System.out.println(result);
				} bufferedReader.close();
				
			
				
		}catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
			System.out.println(result);
			return result;
		}
		

	public static void main (String args[]){
		//testDecrypt("Lwuv c vguv uvtkpi ykvj nqvu qh gggggggggggggggggu");
		//halfOfString ("Qbkm Zgis", 1);
	//	decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
	//	decryptTwoKeysFile("twoKeysPractise.txt");
		String result = combineFile("mysteryTwoKeyQuiz.txt");
		decryptTwoKeys(result);
		
//		WordLengths wl = new WordLengths();
//		wl.countWordLengths("twoKeyPractise.txt", int[] counts);
//		
	//	int[] ex = {1,2,0,4,2};
	//	maxIndex(ex);	
		
	}
	
	
}
