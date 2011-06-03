import net.egork.collections.ArrayUtils;

public class YetAnotherORProblem {
	public int countSequences(long[] R) {
		long[][] result = new long[61][1 << R.length];
		ArrayUtils.fill(result, -1);
		return (int) go(60, (1 << R.length) - 1, result, R);
	}

	private long go(int bit, int mask, long[][] result, long[] R) {
		if (bit < 0)
			return 1;
		if (result[bit][mask] != -1)
			return result[bit][mask];
		int nextMask = mask;
		for (int i = 0; i < R.length; i++) {
			if ((mask >> i & 1) != 0 && (1L << bit & R[i]) != 0)
				nextMask -= 1 << i;
		}
		result[bit][mask] = 0;
		for (int i = 0; i < R.length; i++) {
			if ((mask >> i & 1) == 0)
				result[bit][mask] += go(bit - 1, nextMask, result, R);
			else if ((1L << bit & R[i]) != 0)
				result[bit][mask] += go(bit - 1, nextMask + (1 << i), result, R);
		}
		result[bit][mask] += go(bit - 1, nextMask, result, R);
		result[bit][mask] %= 1000000009;
		return result[bit][mask];
	}

	// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			YetAnotherORProblemHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				YetAnotherORProblemHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherORProblemHarness {
	public static void run_test( int casenum ) {
		if ( casenum != -1 ) {
			if ( runTestCase( casenum ) == -1 )
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for ( int i=0;; ++i ) {
			int x = runTestCase(i);
			if ( x == -1 ) {
				if ( i >= 100 ) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if ( total == 0 ) {
			System.err.println("No test cases run.");
		} else if ( correct < total ) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase( int casenum, int expected, int received ) { 
		System.err.print("Example " + casenum + "... ");
		if ( compareOutput( expected, received ) ) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase( int casenum ) {
		switch( casenum ) {
		case 0: {
			long[] R                  = {3,5};
			int expected__            = 15;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}
		case 1: {
			long[] R                  = {3,3,3};
			int expected__            = 16;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}
		case 2: {
			long[] R                  = {1,128};
			int expected__            = 194;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}
		case 3: {
			long[] R                  = {26,74,25,30};
			int expected__            = 8409;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}
		case 4: {
			long[] R                  = {1000000000,1000000000};
			int expected__            = 420352509;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}

		// custom cases

/*      case 5: {
			long[] R                  = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}*/
/*      case 6: {
			long[] R                  = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}*/
/*      case 7: {
			long[] R                  = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherORProblem().countSequences( R ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


