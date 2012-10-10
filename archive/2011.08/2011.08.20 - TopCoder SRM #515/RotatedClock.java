public class RotatedClock {
	public String getEarliest(int hourHand, int minuteHand) {
		int minHour = Integer.MAX_VALUE;
		for (int i = 0; i < 360; i += 30) {
			int curHour = (hourHand + i) % 360;
			int curMinute = (minuteHand + i) % 360;
			if (curHour % 30 * 12 == curMinute) {
				minHour = Math.min(minHour, curHour);
			}
		}
		if (minHour == Integer.MAX_VALUE)
			return "";
		int hour = minHour / 30;
		int minute = minHour % 30 * 2;
		StringBuilder result = new StringBuilder();
		result.append(hour / 10);
		result.append(hour % 10);
		result.append(":");
		result.append(minute / 10);
		result.append(minute % 10);
		return result.toString();
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			RotatedClockHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				RotatedClockHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RotatedClockHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum) {
		switch(casenum) {
		case 0: {
			int hourHand              = 70;
			int minuteHand            = 300;
			String expected__         = "08:20";

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}
		case 1: {
			int hourHand              = 90;
			int minuteHand            = 120;
			String expected__         = "11:00";

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}
		case 2: {
			int hourHand              = 240;
			int minuteHand            = 36;
			String expected__         = "";

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}
		case 3: {
			int hourHand              = 19;
			int minuteHand            = 19;
			String expected__         = "";

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}
		case 4: {
			int hourHand              = 1;
			int minuteHand            = 12;
			String expected__         = "00:02";

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}

		// custom cases

/*      case 5: {
			int hourHand              = ;
			int minuteHand            = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}*/
/*      case 6: {
			int hourHand              = ;
			int minuteHand            = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}*/
/*      case 7: {
			int hourHand              = ;
			int minuteHand            = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new RotatedClock().getEarliest(hourHand, minuteHand));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


