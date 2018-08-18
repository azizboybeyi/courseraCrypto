package aziz;

public class WordPlay {
	
	/* checks if input char ch is a vowel, returns true if is vowel */
	public static boolean isVowel(char ch) {
		
		char uch = Character.toUpperCase(ch);
		
		if (uch == 'A' || uch == 'E' || uch == 'I' || uch == 'O' || uch == 'U'){
			System.out.println("Character entered, " + ch + " is a vowel");
			return true;
		} 
	return false;
	
	} 
	
	
	public static String replaceVowel (String phrase, char ch){
			
		StringBuilder str = new StringBuilder(phrase);
		
		for (int k=0; k < str.length(); k++){
			if (isVowel(str.charAt(k))){
				str.setCharAt(k, ch);
			}
		}
		
		return str.toString();
	}
	
	
	public static String emphasize(String phrase, char ch){
		StringBuilder str = new StringBuilder(phrase);
		int[] test = {1, 2, 3};
		int testNum = 5;
		
		for(int k = 0; k< str.length(); k++){
			if (str.charAt(k) == ch){
				replaceChar(str, testNum, test);
				System.out.println(test[0]);
				System.out.println(str.toString());
				System.out.println(testNum);			
				}
		}
		
		return str.toString();
	}
	
	public static void replaceChar(StringBuilder sb, int index, int[] changeMe){
		changeMe[0] = 5000;
		if (index % 2 == 0){
			sb.setCharAt(index, '*');
		}
		else{
		sb.setCharAt(index, '+');
	}	
	index = 1000;
	}
	
		
public static void main (String args[]){
	
	char c = 65;
	
	//System.out.print(c);
	//isVowel(c);
	
	//System.out.println(replaceVowel ("hello world", '*'));
	emphasize("dna ctgaaactga", 'a');
	//System.out.println(emphasize("dna ctgaaactga", 'a'));
	//dn* ctg+*+ctg+
	
	}
}
