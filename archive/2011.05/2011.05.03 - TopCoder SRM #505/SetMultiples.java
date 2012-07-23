public class SetMultiples {
	public long smallestSubset(long A, long B, long C, long D) {
		A = Math.max(A, B / 2 + 1);
		long start = A;
		long result = 0;
		while (start != B + 1) {
			long ratio = D / start;
			long end = Math.min(D / ratio, B);
			long goodStart = Math.min((C + ratio - 1) / ratio, end + 1);
			result += Math.max(goodStart - start, 0);
			start = end + 1;
		}
		C = Math.max(C, D / 2 + 1);
		result += D - C + 1;
		return result;
	}

// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			SetMultiplesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SetMultiplesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SetMultiplesHarness {
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
			long A                    = 1L;
			long B                    = 1L;
			long C                    = 2L;
			long D                    = 2L;
			long expected__           = 1L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}
		case 1: {
			long A                    = 1L;
			long B                    = 2L;
			long C                    = 3L;
			long D                    = 4L;
			long expected__           = 2L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}
		case 2: {
			long A                    = 2L;
			long B                    = 3L;
			long C                    = 5L;
			long D                    = 7L;
			long expected__           = 3L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}
		case 3: {
			long A                    = 1L;
			long B                    = 10L;
			long C                    = 100L;
			long D                    = 1000L;
			long expected__           = 500L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}
		case 4: {
			long A                    = 1000000000L;
			long B                    = 2000000000L;
			long C                    = 9000000000L;
			long D                    = 10000000000L;
			long expected__           = 1254365078L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}

		// custom cases

/*      case 5: {
			long A                    = L;
			long B                    = L;
			long C                    = L;
			long D                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}*/
/*      case 6: {
			long A                    = L;
			long B                    = L;
			long C                    = L;
			long D                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}*/
/*      case 7: {
			long A                    = L;
			long B                    = L;
			long C                    = L;
			long D                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new SetMultiples().smallestSubset(A, B, C, D));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


