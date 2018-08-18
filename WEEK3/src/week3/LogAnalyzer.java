package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogAnalyzer {

	private static ArrayList<LogEntry> records = new ArrayList<LogEntry>();

	public LogAnalyzer() {

		records = new ArrayList<LogEntry>();
	}

	public void readFile(String filename) {

		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(filename);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			for (line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {

				line.trim();

				if (!line.isEmpty()) {

					LogEntry LE = WebLogParser.parseEntry(line);

					records.add(LE);
					// System.out.println("************************************************************");
				}
			}
			System.out.println("AL after loop" + Arrays.toString(records.toArray()) + "\n");
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filename + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + filename + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	public void printAll() {
		for (LogEntry le : records) {
			System.out.println(le);
		}
	}

	public static int countUniqueIPs() {
		ArrayList<String> uniqueIPs = new ArrayList<String>();

		for (LogEntry le : records) {
			String ipAddr = le.getIpAddress();

			if (!uniqueIPs.contains(ipAddr)) {
				uniqueIPs.add(ipAddr);
			}
		}
		System.out.println("number of unique IPs is:    " + uniqueIPs.size());
		return uniqueIPs.size();
	}

	public static void printAllHigherThanNum(int num) {

		for (LogEntry le : records) {
			if (le.getStatusCode() > num) {
				System.out.println(le);
			}
		}
	}

	public static ArrayList<String> uniqueIPVisitsOnDay(String someday) {
		ArrayList<String> IPsOnDay = new ArrayList<String>();
		System.out.println("IPsOnDay AL before loop:   " + Arrays.toString(IPsOnDay.toArray()));
		System.out.println("RECORDS AL:   " + Arrays.toString(records.toArray()));
		System.out.println("***********************************************************************");

		for (LogEntry le : records) {

			// LogEntry.getAccessTime();

			String str = le.getAccessTime().toString().substring(4, 10);
			// System.out.println("Date is: " + str);

			if (str.equals(someday) && !IPsOnDay.contains(le.getIpAddress())) {
				// System.out.println("TESTTTTTTTTTT");
				IPsOnDay.add(le.getIpAddress());
				System.out.println("IPsOnDay AL:	" + Arrays.toString(IPsOnDay.toArray()));
			}
		}
		System.out.println("AFTER LOOP AL:	" + Arrays.toString(IPsOnDay.toArray()));
		System.out.println(IPsOnDay.size());
		return IPsOnDay;
	}

	public int countUniqueIPsInRange(int low, int high) {
		int numOfUniqueIPs = 0;
		ArrayList<String> IPs = new ArrayList<String>();

		for (LogEntry le : records) {
			if (le.getStatusCode() >= low && le.getStatusCode() <= high && !IPs.contains(le.getIpAddress())) {
				IPs.add(le.getIpAddress());
				numOfUniqueIPs = IPs.size();
			}
		}
		System.out.println("number of unique IPs between " + low + " and high " + high + " is:  " + numOfUniqueIPs);
		return numOfUniqueIPs;
	}

	public HashMap<String, Integer> countVisitsPerIP() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();

		for (LogEntry le : records) {
			//if(date!=null && le.getAccessTime().toString().equals(date))
			String ip = le.getIpAddress();

			if (!counts.containsKey(ip)) {
				counts.put(ip, 1);
			} else {
				counts.put(ip, counts.get(ip) + 1);
			}

		}
		System.out.println(counts);
		return counts;
	}
	
	public static int getOccurenceForIP(String targetIP, ArrayList<String> ips) {
		int count = 0;
		for(String ip : ips){
			if(targetIP.equals(ip)){
				count++;
			}
		}
		return count;
	}
	
	
	public HashMap<String, Integer> countVisitsPerIPForDate(String date) {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();

		for (LogEntry le : records) {
			if(le.getAccessTime().toString().equals(date)) {
				System.out.println("date is:   "+le.getAccessTime());
				String ip = le.getIpAddress();

				if (!counts.containsKey(ip)) {
					counts.put(ip, 1);
				} else {
					counts.put(ip, counts.get(ip) + 1);
				}	
				
			}

		}
		System.out.println(counts);
		return counts;
	}

	public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {

		int currval = 0;

		for (HashMap.Entry<String, Integer> entry : counts.entrySet()) {
			if (entry.getValue() > currval) {
				currval = entry.getValue();

			}
		}
		System.out.println("Max Num of Visits to site:    " + currval);
		return currval;
	}

	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
		LogAnalyzer LA = new LogAnalyzer();
		int maxNum = LA.mostNumberVisitsByIP(counts);

		ArrayList<String> al = new ArrayList<String>();

		for (HashMap.Entry<String, Integer> entry : counts.entrySet()) {
			if (entry.getValue() == maxNum) {
				al.add(entry.getKey());
			}
		}
		System.out.println(al);
		return al;
	}

	public HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();

		for (LogEntry le : records) {
			String date = le.getAccessTime().toString().substring(4, 10);

			if (!myMap.containsKey(date)) {
				ArrayList<String> ips = new ArrayList<String>();
				ips.add(le.getIpAddress());

				myMap.put(date, ips);
			} else {
				// if (!myMap.get(date).contains(le.getIpAddress())) { //if you
			                                                           // dont want to repeat IPs, use this

				myMap.get(date).add(le.getIpAddress());
				// }
			}

		}
		System.out.println(myMap);
		return myMap;
	}

	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts) {

		int currval = 0;
		String date = "";

		for (HashMap.Entry<String, ArrayList<String>> entry : counts.entrySet()) {
			if (entry.getValue().size() > currval) {
				currval = entry.getValue().size();
				date = entry.getKey();
			}
		}
		System.out.println("Date with Most IP Visits is:    " + date);
		return date;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String> > counts, String date){
		HashMap<String, Integer> myMap = new HashMap<String, Integer> ();
		int currentMax = Integer.MIN_VALUE;
		ArrayList<String> ips = new ArrayList<String>();   // where we'll store ips with the most occurances
		
		//Loop through map and check if the key matches the date
		for (HashMap.Entry<String, ArrayList<String> > entry : counts.entrySet()){
			if (entry.getKey().equals(date)){
				for(String s : entry.getValue()){ //loop through each ip in the array list
					int occ = getOccurenceForIP(s, entry.getValue()); //get an occurence for each ip
					if(occ > currentMax) { //compare with current max, find the maximum count and store it
						currentMax = occ;
					}
				}
				
			}
		}
		//loop through hashmap again and make sure dates are equal
		for (HashMap.Entry<String, ArrayList<String> > entry : counts.entrySet()){
			if (entry.getKey().equals(date)){
				for(String s : entry.getValue()){ // loop through ips again
					int occ = getOccurenceForIP(s, entry.getValue()); //get the occurence for each ip again
					if(occ == currentMax) {
					 //add the ip and the occurence to map only if occurence is equal to current max
						myMap.put(s, occ);
					}
				}
				
			}
		}
		System.out.println("For date " + date + " there were " +myMap);
		return ips;  
	}
	
	
	
	
}
