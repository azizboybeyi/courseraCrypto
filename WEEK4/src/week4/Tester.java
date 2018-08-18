package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

	public static void testCaesarCipher(String fileName, int key) {
		CaesarCipher cc = new CaesarCipher(key);
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				line.trim();
				System.out.println(cc.decrypt(line));
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

	public static void testCaesarCracker(String fileName) {
		CaesarCracker cc = new CaesarCracker(); // put most common letter of the
		// language

		String line = "";
		String lineComb = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				line.trim();
				lineComb.trim();
				lineComb += " " + line;

				// System.out.println(cc.decrypt(lineComb));
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
		System.out.println(cc.decrypt(lineComb));
	}

	public static void testVigenereCipher(String fileName) {
		int[] keys = { 17, 14, 12, 4 };
		VigenereCipher VC = new VigenereCipher(keys);

		String line = "";
		String lineComb = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				line.trim();
				lineComb.trim();
			//	lineComb += " " + line;
				System.out.println(VC.encrypt(line));
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

	//	System.out.println(VC.encrypt(lineComb));

	}
	
	public static void testTryKeyLength(String fileName){
		
		VigenereBreaker VB = new VigenereBreaker();
		
		
		String line = "";
		String lineComb = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
//				line.trim();
				lineComb += line + " ";
				//lineComb.trim();
			}	lineComb.trim(); 			//System.out.println(lineComb.trim());

			
			
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		VB.tryKeyLength(lineComb, 5, 'e');
		
	}

	public static void testBreakVigenere(String fileName){
		VigenereBreaker VB =new VigenereBreaker();
		VB.breakVigenere(fileName);
//		VB.readDictionary("English.txt");
		
	}
	
	public static void main(String args[]) {
		// testCaesarCipher("titus-small_key5.txt", 5);
//		testCaesarCracker("titus-small_key5.txt");
//		testVigenereCipher("titus-small.txt"); 
//		testTryKeyLength("athens_keyflute.txt"); 
		testBreakVigenere("secretmessage4.txt");
		
	}
}
