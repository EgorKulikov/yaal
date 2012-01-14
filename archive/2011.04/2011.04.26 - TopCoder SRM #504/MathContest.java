public class MathContest {
	public int countBlack(String ballSequence, int repetitions) {
		StringBuilder builder = new StringBuilder(ballSequence.length() * repetitions);
		for (int i = 0; i < repetitions; i++)
			builder.append(ballSequence);
		char[] sequence = builder.toString().toCharArray();
		int pointer = 0;
		boolean invert = false;
		int diff = 1;
		int start = 0;
		int end = sequence.length - 1;
		int result = 0;
		while (start <= end) {
			boolean isWhite = sequence[pointer] == 'W' ^ invert;
			pointer += diff;
			if (diff == 1)
				start++;
			else
				end--;
			if (isWhite) {
				if (diff == 1) {
					pointer = end;
					diff = -1;
				} else {
					pointer = start;
					diff = 1;
				}
			} else {
				invert = !invert;
				result++;
			}
		}
		return result;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			MathContestHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				MathContestHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MathContestHarness {
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
			String ballSequence       = "BBWWB";
			int repetitions           = 1;
			int expected__            = 2;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}
		case 1: {
			String ballSequence       = "BBB";
			int repetitions           = 10;
			int expected__            = 1;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}
		case 2: {
			String ballSequence       = "BW";
			int repetitions           = 10;
			int expected__            = 20;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}
		case 3: {
			String ballSequence       = "WWWWWWWBWWWWWWWWWWWWWW";
			int repetitions           = 1;
			int expected__            = 2;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}

		// custom cases

/*      case 4: {
			String ballSequence       = ;
			int repetitions           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}*/
/*      case 5: {
			String ballSequence       = ;
			int repetitions           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}*/
/*      case 6: {
			String ballSequence       = ;
			int repetitions           = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MathContest().countBlack( ballSequence, repetitions ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


