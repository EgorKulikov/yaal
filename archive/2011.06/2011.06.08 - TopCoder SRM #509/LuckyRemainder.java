public class LuckyRemainder {
	public int getLuckyRemainder(String X) {
		int length = X.length();
		long result = 0;
		for (char digit : X.toCharArray())
			result += (1L << (length - 1)) * (digit - '0');
		return (int) (result % 9);
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			LuckyRemainderHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				LuckyRemainderHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class LuckyRemainderHarness {
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
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
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
			String X                  = "123";
			int expected__            = 6;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}
		case 1: {
			String X                  = "24816";
			int expected__            = 3;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}
		case 2: {
			String X                  = "8";
			int expected__            = 8;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}
		case 3: {
			String X                  = "11235813213455";
			int expected__            = 7;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}

		// custom cases

/*      case 4: {
			String X                  = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}*/
/*      case 5: {
			String X                  = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}*/
/*      case 6: {
			String X                  = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new LuckyRemainder().getLuckyRemainder(X));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


