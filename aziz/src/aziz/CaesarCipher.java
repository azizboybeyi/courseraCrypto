package aziz;

public class CaesarCipher {

//	public static String encrypt(String input, int key) {
//
//		StringBuilder str = new StringBuilder(input);
//
//		String alphabet = "abcdefghijklmnopqrstuvwxyz";
//		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
//
//		StringBuilder ShiftedSbAlphabet = new StringBuilder(shiftedAlphabet);
//
		// for (int k=0 ; k < input.length(); k++){
		//
		//
		// char currChar = str.charAt(k);
		// String strCurrChar = String.valueOf(currChar);
		// int idx = alphabet.indexOf(strCurrChar);
		//
		// if (currChar.isAlphabetic(k)){
		// char newChar = shiftedAlphabet.charAt(idx);
		// str.setCharAt(str.charAt(k), newChar);
		// }
		// return str.toString();
		//
		//
		// }
//
//		for (int k = 0; k < input.length(); k++) {
//
//			char currChar = str.charAt(k); // gets current character
//			String strCurrChar = String.valueOf(currChar); // converts current
//															// character to
//															// string
//			
//			
//			if (Character.isLetter(currChar)){ //checks if current character is a letter
//		        int idx = alphabet.indexOf(currChar); //finds position of current character
//		        int newPos = (idx + key) % 26; //calculates new position
//		        char newChar = alphabet.charAt(newPos); //finds the character at that new position
//		        if(Character.isUpperCase(currChar)) { //if original character is upper case, make new one upper case
//		         	str.setCharAt(k, Character.toUpperCase(newChar));  
//		        }
//		        else{ //otherwise, it must have been lower case, so make new one lower case too
//		          str.setCharAt(k, Character.toLowerCase(currChar));
//		        }
//					
//		      } 
//		      else { //what happens if not a character? just append current char as is yep
//		        str.setCharAt(k, currChar);
//		      }
//					
//				} 	
//				return str.toString();
//			}

	

/*Create a new class called CaesarCipher.
Write the method encrypt that has two parameters, a String named input and an int named key. This method returns a String that has been encrypted using the Caesar Cipher algorithm explained in the videos. Assume that all the alphabetic characters are uppercase letters. For example, the call
encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23)

should return the string

“CFOPQ IBDFLK XQQXZH BXPQ CIXKH!” */


	public static String encrypt (String input, int key){
		
		StringBuilder str = new StringBuilder(input);
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

    for (int k=0 ; k < input.length(); k++){
		
			char currChar = str.charAt(k); //gets current character 
	
			if (currChar!=' ' && Character.isLetter(currChar)){ //checks if current character is a letter
        int idx = alphabet.indexOf(Character.toLowerCase(currChar)); //finds position of current character CONVERT TO LOWER CASE
        int newPos = (idx + key) % 26; //calculates new position
        char newChar = alphabet.charAt(newPos); //finds the character at that new position
        if(Character.isUpperCase(currChar)) { //if original character is upper case, make new one upper case
         	str.setCharAt(k, Character.toUpperCase(newChar));  
        }
        else{ //otherwise, it must have been lower case, so make new one lower case too
          str.setCharAt(k, Character.toLowerCase(newChar));
        }
			
      } 
      else { //what happens if not a character? just append current char as is yep
        str.setCharAt(k, currChar);
      }
			
		} 	
		return str.toString();
	}


	
	
	
	public static String encryptTwoKeys(String input, int key1, int key2) {

		StringBuilder str = new StringBuilder(input);

		String alphabet = "abcdefghijklmnopqrstuvwxyz";

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
																				// character
																				// CONVERT
																				// TO
																				// LOWER
																				// CASE
				
        int newPos = 0; //initialize it here, before the if-else, so rest of code in for-loop can see it
				if (k % 2 == 0){
					 newPos = (idx + key1) % 26; // calculates new position
				} else {
					 newPos = (idx + key2) % 26; // calculates new position
				}
				
				char newChar = alphabet.charAt(newPos); // finds the character
														// at that new position
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

		
	
	
	
	
	
	
	
	public static void main(String args[]) {

		System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
	//	System.out.println("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!");
		System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
	//	System.out.println("Czojq Ivdzle");
		
		System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx",24 ,6));
	}
}