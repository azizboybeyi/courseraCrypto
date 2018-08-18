package week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
	public static void testLogEntry() {
		LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
		System.out.println(le);
		LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
		System.out.println(le2);
	}

	public static void testLogAnalyzer() {

		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("short-test_log.txt");
		LA.printAll();

	}

	public static void testUniqueIP() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");
		LogAnalyzer.countUniqueIPs();

	}

	public static void testHigherThanNum(int num) {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog1_log.txt");
		LogAnalyzer.printAllHigherThanNum(num);
	}

	public static void testIPVisitsOnDay() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");
		LA.uniqueIPVisitsOnDay("Sep 24");// Mon Sep 14 13:00:32 BST 2015
	}

	public static void testCountUniqueIPsInRange() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");
		LA.countUniqueIPsInRange(400, 499);
	}

	public static void testCountVisitsPerIP() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("short-test_log.txt");
		LA.countVisitsPerIP();
	}

	public static void testMostNumberVisitsByIP() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");

		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		counts = LA.countVisitsPerIP();
		LA.mostNumberVisitsByIP(counts);
	}

	public static void testIPsMostVisits() {
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");

		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		counts = LA.countVisitsPerIP();
		LA.iPsMostVisits(counts);

	}
	
	public static void testIPsForDays(){
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog3-short_log.txt");
		
		LA.iPsForDays(); 
	}
	
	public static void testDayWithMostIPVisits(){
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");
		LA.dayWithMostIPVisits(LA.iPsForDays());
	}

	public static void testIPsWithMostVisitsOnDay(){
		LogAnalyzer LA = new LogAnalyzer();
		LA.readFile("weblog2_log.txt");
	
		HashMap<String, ArrayList<String> > myMap = new HashMap<String, ArrayList<String>> ();
		myMap = LA.iPsForDays();
		LA.iPsWithMostVisitsOnDay(myMap, "Sep 30");  
		
	}
	
	
	
	public static void main(String args[]) {
		// testLogAnalyzer();
		// testLogEntry();
//		 testUniqueIP();
		// testHigherThanNum(400);
//		 testIPVisitsOnDay();
//		 testCountUniqueIPsInRange();
//		 testCountVisitsPerIP();
//		 testMostNumberVisitsByIP();
//		 testIPsMostVisits();
//		testIPsForDays();
//		testDayWithMostIPVisits();
		testIPsWithMostVisitsOnDay();
	}
}