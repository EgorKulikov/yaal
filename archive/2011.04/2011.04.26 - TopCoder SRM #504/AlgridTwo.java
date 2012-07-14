public class AlgridTwo {
	private static final long MOD = 1000000007;

	public int makeProgram(String[] output) {
		char[][] table = new char[output.length][];
		for (int i = 0; i < output.length; i++)
			table[i] = output[i].toCharArray();
		long result = 1;
		for (int i = table.length - 2; i >= 0; i--) {
			long resultBlack = table[i + 1][table[i].length - 1] == 'B' ? result : 0;
			long resultWhite = table[i + 1][table[i].length - 1] == 'W' ? result : 0;
			for (int j = table[i].length - 2; j >= 0; j--) {
				char c1 = table[i][j];
				char c2 = table[i][j + 1];
				char r1 = table[i + 1][j];
				if (c1 == 'W' && c2 == 'W') {
					if (r1 == 'W') {
						resultWhite = (resultWhite + resultBlack) % MOD;
						resultBlack = 0;
					} else {
						resultBlack = (resultWhite + resultBlack) % MOD;
						resultWhite = 0;
					}
					continue;
				}
				if (c1 == 'B' && c2 == 'B')
					continue;
				if (r1 != c1)
					return 0;
				long currentResult;
				if (r1 == 'W')
					currentResult = (2 * resultWhite) % MOD;
				else
					currentResult = (2 * resultBlack) % MOD;
				resultWhite = currentResult;
				resultBlack = currentResult;
			}
			result = (resultWhite + resultBlack) % MOD;
		}
		return (int) result;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			AlgridTwoHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				AlgridTwoHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class AlgridTwoHarness {
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
			String[] output           = {"BB",  "WB"};
			int expected__            = 1;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}
		case 1: {
			String[] output           = {"BBWBBB",  "WBWBBW"};
			int expected__            = 8;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}
		case 2: {
			String[] output           = {"BWBWBW",  "WWWWWW",  "WBBWBW"};
			int expected__            = 0;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}
		case 3: {
			String[] output           = {"WWBWBWBWBWBWBWBW",  "BWBWBWBWBWBWBWBB",  "BWBWBWBWBWBWBWBW"};
			int expected__            = 73741817;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}

		// custom cases

/*      case 4: {
			String[] output           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}*/
/*      case 5: {
			String[] output           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}*/
/*      case 6: {
			String[] output           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new AlgridTwo().makeProgram( output ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


