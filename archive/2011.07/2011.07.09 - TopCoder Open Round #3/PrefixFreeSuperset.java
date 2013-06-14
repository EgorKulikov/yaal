import java.math.BigInteger;

public class PrefixFreeSuperset {
	public long minSumLength(String[] cur, long k) {
		k -= cur.length;
		if (k == 0) {
			long base = 0;
			for (String s : cur)
				base += s.length();
			return base;
		}
		int[] lengths = new int[52];
		go(cur, lengths, "");
		boolean good = false;
		for (int length : lengths) {
			if (length != 0) {
				good = true;
			}
		}
		if (!good)
			return -1;
		long last = 0;
		for (int i = 0; ; i++) {
			long current = (last << 1) + (i < lengths.length ? lengths[i] : 0);
			if (current >= k) {
				long excess = current - k;
				long lower = Math.min(last, excess);
				long base = 0;
				for (String s : cur)
					base += s.length();
				BigInteger result = BigInteger.valueOf(base);
				result = result.add(BigInteger.valueOf(k).multiply(BigInteger.valueOf(i)));
				result = result.subtract(BigInteger.valueOf(lower));
				if (result.compareTo(BigInteger.valueOf(1000000000000000000L)) > 0)
					return -2;
				return result.longValue();
			}
			last = current;
		}
	}

	private void go(String[] cur, int[] lengths, String prefix) {
		for (String s : cur) {
			if (s.equals(prefix))
				return;
		}
		for (String s : cur) {
			if (s.startsWith(prefix)) {
				go(cur, lengths, prefix + "0");
				go(cur, lengths, prefix + "1");
				return;
			}
		}
		lengths[prefix.length()]++;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			PrefixFreeSupersetHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PrefixFreeSupersetHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PrefixFreeSupersetHarness {
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
			String[] cur              = {"010"};
			long k                    = 4L;
			long expected__           = 9L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 1: {
			String[] cur              = {"01","000"};
			long k                    = 4L;
			long expected__           = 9L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 2: {
			String[] cur              = {"0011","011110101","11101010111","11101010100000000","11101010100000001111"};
			long k                    = 1000000000000L;
			long expected__           = 39971901640560L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 3: {
			String[] cur              = {"010","00","011","1"};
			long k                    = 4L;
			long expected__           = 9L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 4: {
			String[] cur              = {"010","00","011","1"};
			long k                    = 5L;
			long expected__           = -1L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}

		// custom cases

/*      case 5: {
			String[] cur              = ;
			long k                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
/*      case 6: {
			String[] cur              = ;
			long k                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
/*      case 7: {
			String[] cur              = ;
			long k                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


