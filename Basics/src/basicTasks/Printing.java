package basicTasks;

public class Printing {

	//This is how you write a java comment, lines that begin like this are ignored by your compiler
	
	
	public Printing() { //this thing is called the class constructor, we do not need this right now
		
	}

  
  //we are now BEFORE the main method, this is where our functions should be defined
  //we are gonna make a few simple methods and then run them in our main method
  
  public static boolean nameComparitor(String someonesName) { //this method takes in an INPUT, someonesName, and it returns a BOOL (true or false)
  
    String name = "aziz"; //what i did here was to initialize a STRING to "aziz"
    
    boolean nameMatch = false; //here, i am making a boolean variable: it can either be true, or false
    
    if(someonesName==name) { //here, i am checking to see if the input name MATCHES (==) the name defined inside my function
    
      nameMatch = true; //if a match is found, nameMatch is set to true
      System.out.println("Does the input " + someonesName + " match with " + name + " ?    " + nameMatch);
     
    
    }
    
    else {
    	System.out.println("Does the input " + someonesName + " match with " + name + " ?    " + nameMatch);
   
    
    }
    
    
    return nameMatch;
  
  }

  
	public static void main(String[] args) { //this is your MAIN method, it is where you run your program
  
    String testName = "aziz"; //here, i am initializing a variable called testName. i have set that variable = to "AZIZ"
    nameComparitor(testName); //here, i am PASSING the testName variable INSIDE the function above, namePrinter
    
    String testNameTwo = "ghadeer";
    nameComparitor(testNameTwo);
    

	}

}