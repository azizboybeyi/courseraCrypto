package aziz;

public class CaesarCipherNew {
	private static String alphabet;
	private static String shiftedAlphabet;
	private static int mainKey;

	public CaesarCipherNew (int key){
		 alphabet = "abcdefghijklmnopqrstuvwxyz";
		 shiftedAlphabet =  alphabet.substring(key) + alphabet.substring(0, key);
		 mainKey = key;
	}
	
	public static String encrypt(String input) {

		StringBuilder str = new StringBuilder(input);
		
		for (int k = 0; k < input.length(); k++) {

			char currChar = str.charAt(k); // gets current character

			if (currChar != ' ' && Character.isLetter(currChar)) { // checks if current character is a letter
				
				int idx = alphabet.indexOf(Character.toLowerCase(currChar)); // finds position of current
																		// character CONVERT TO LOWERCASE
			
				char newChar = shiftedAlphabet.charAt(idx); // finds the character at that new position
				
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
	
	
	public  String decrypt (String input){
		CaesarCipherNew cc = new CaesarCipherNew(26 - mainKey);
		String message = cc.encrypt(input);
	//	System.out.println(message);
		return message;
	}
	
	

public static void main(String args[]){

	CaesarCipherNew cc = new CaesarCipherNew(15); 
	System.out.println(cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"));
	System.out.println("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!");
	
	System.out.println(mainKey);
	
	CaesarCipherNew dd = new CaesarCipherNew(mainKey);
	System.out.println(dd.decrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!"));
	
	System.out.println(mainKey);
}
}