package aziz;

public class CaesarCipherTwo {

	private static String alphabet;
	private static String shiftedAlphabet1;
	private static String shiftedAlphabet2;
	private static int dkey1;
	private static int dkey2;

	public CaesarCipherTwo(int key1, int key2) {
		alphabet = "abcdefghijklmnopqrstuvwxyz";
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
		dkey1 = 26 - key1;
		dkey2 = 26 - key2;

	}

	public static String encrypt(String input) {

		StringBuilder str = new StringBuilder(input);

		for (int k = 0; k < input.length(); k++) {
			char currChar = str.charAt(k); // gets current character

			if (currChar != ' ' && Character.isLetter(currChar)) { // checks if
				// current
				// character
				// is a
				// letter

				int idx = alphabet.indexOf(Character.toLowerCase(currChar)); // finds
				// position
				// of
				// current
				// character CONVERT TO LOWERCASE

				char newChar = shiftedAlphabet2.charAt(idx);

				if (k % 2 == 0) {
					newChar = shiftedAlphabet1.charAt(idx); // finds the
				}
				// character at that
				// new position

				if (Character.isUpperCase(currChar)) { // if original character
					// is upper case, make
					// new one upper case
					str.setCharAt(k, Character.toUpperCase(newChar));

				} else { // otherwise, it must have been lower case, so make new
					// one lower case too
					str.setCharAt(k, Character.toLowerCase(newChar));
				}
			} else { // what happens if not a character? just append current
				// char as is yep
				str.setCharAt(k, currChar);
			}
		}
		return str.toString();
	}

	public String decrypt(String input) {

		StringBuilder str = new StringBuilder(input);

		for (int k = 0; k < input.length(); k++) {
			char currchar = input.charAt(k);

			if (currchar != ' ' && Character.isLetter(currchar)) {

				int idx = shiftedAlphabet2.indexOf(Character.toLowerCase(currchar));

				if (k % 2 == 0) {
					idx = shiftedAlphabet1.indexOf(Character.toLowerCase(currchar));
				}

				char newChar = alphabet.charAt(idx);

				if (Character.isUpperCase(currchar)) { // if original character
					// is upper case, make
					// new one upper case
					str.setCharAt(k, Character.toUpperCase(newChar));

				} else { // otherwise, it must have been lower case, so make new
					// one lower case too
					str.setCharAt(k, Character.toLowerCase(newChar));

				}
			} else { // what happens if not a character? just append current
				// char as is yep
				str.setCharAt(k, currchar);
			}
		}
		return str.toString();
	}

	
	
	public static void main(String args[]){
		CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
		System.out.println(cc.encrypt("Hello World !?"));
		System.out.println(cc.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
		
		
	}
}