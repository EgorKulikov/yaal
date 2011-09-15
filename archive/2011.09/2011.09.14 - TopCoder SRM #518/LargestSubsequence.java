public class LargestSubsequence {
	public String getLargest(String s) {
		StringBuilder result = new StringBuilder();
		int index = 0;
		while (index != s.length()) {
			int max = 'a' - 1;
			int maxIndex = -1;
			for (int i = index; i < s.length(); i++) {
				if (s.charAt(i) > max) {
					max = s.charAt(i);
					maxIndex = i;
				}
			}
			result.append(s.charAt(maxIndex));
			index = maxIndex + 1;
		}
		return result.toString();
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			LargestSubsequenceHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				LargestSubsequenceHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class LargestSubsequenceHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase( int casenum, String expected, String received ) { 
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
			String s                  = "test";
			String expected__         = "tt";

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}
		case 1: {
			String s                  = "a";
			String expected__         = "a";

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}
		case 2: {
			String s                  = "example";
			String expected__         = "xple";

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}
		case 3: {
			String s                  = "aquickbrownfoxjumpsoverthelazydog";
			String expected__         = "zyog";

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}

		// custom cases

/*      case 4: {
			String s                  = ;
			String expected__         = ;

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}*/
/*      case 5: {
			String s                  = ;
			String expected__         = ;

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}*/
/*      case 6: {
			String s                  = ;
			String expected__         = ;

			return verifyCase( casenum, expected__, new LargestSubsequence().getLargest( s ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


