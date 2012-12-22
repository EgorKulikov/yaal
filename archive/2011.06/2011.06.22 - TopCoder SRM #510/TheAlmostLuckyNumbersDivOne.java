public class TheAlmostLuckyNumbersDivOne {
	public long find(long a, long b) {
		long[] lucky = {0};
		long[] almostLucky = {};
		long answer = 0;
		for (int i = 0; i < 16; i++) {
			long[] nextLucky = new long[2 * lucky.length];
			for (int j = 0; j < lucky.length; j++) {
				nextLucky[2 * j] = 10 * lucky[j] + 4;
				nextLucky[2 * j + 1] = 10 * lucky[j] + 7;
			}
			long[] nextAlmostLucky = new long[2 * almostLucky.length + 8 * lucky.length - (i == 0 ? 1 : 0)];
			for (int j = 0; j < almostLucky.length; j++) {
				nextAlmostLucky[2 * j] = 10 * almostLucky[j] + 4;
				nextAlmostLucky[2 * j + 1] = 10 * almostLucky[j] + 7;
			}
			for (int j = 0; j < lucky.length; j++) {
				if (i != 0)
					nextAlmostLucky[almostLucky.length * 2 + 8 * j + 7] = 10 * lucky[j] + 0;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 1] = 10 * lucky[j] + 1;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 2] = 10 * lucky[j] + 2;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 3] = 10 * lucky[j] + 3;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 4] = 10 * lucky[j] + 5;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 5] = 10 * lucky[j] + 6;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 6] = 10 * lucky[j] + 8;
				nextAlmostLucky[almostLucky.length * 2 + 8 * j + 0] = 10 * lucky[j] + 9;
			}
			lucky = nextLucky;
			almostLucky = nextAlmostLucky;
			for (long v : lucky) {
				if (v >= a && v <= b)
					answer++;
			}
			for (long v : almostLucky) {
				if (v >= a && v <= b)
					answer++;
			}
		}
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			TheAlmostLuckyNumbersDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheAlmostLuckyNumbersDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheAlmostLuckyNumbersDivOneHarness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
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
			long a                    = 4L;
			long b                    = 7L;
			long expected__           = 4L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}
		case 1: {
			long a                    = 8L;
			long b                    = 19L;
			long expected__           = 4L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}
		case 2: {
			long a                    = 28L;
			long b                    = 33L;
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}
		case 3: {
			long a                    = 12345678900L;
			long b                    = 98765432100L;
			long expected__           = 91136L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}

		// custom cases

/*      case 4: {
			long a                    = L;
			long b                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}*/
/*      case 5: {
			long a                    = L;
			long b                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}*/
/*      case 6: {
			long a                    = L;
			long b                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new TheAlmostLuckyNumbersDivOne().find(a, b));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


