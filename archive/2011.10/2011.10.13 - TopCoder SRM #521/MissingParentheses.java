public class MissingParentheses {
	public int countCorrections(String par) {
		int min = 0;
		int current = 0;
		for (int i = 0; i < par.length(); i++) {
			if (par.charAt(i) == ')')
				current--;
			else
				current++;
			min = Math.min(min, current);
		}
		return -2 * min + current;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			MissingParenthesesHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				MissingParenthesesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MissingParenthesesHarness {
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
			String par                = "(()(()";
			int expected__            = 2;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}
		case 1: {
			String par                = "()()(()";
			int expected__            = 1;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}
		case 2: {
			String par                = "(())(()())";
			int expected__            = 0;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}
		case 3: {
			String par                = "())(())((()))))()((())))()())())())()()()";
			int expected__            = 7;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}

		// custom cases

/*      case 4: {
			String par                = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}*/
/*      case 5: {
			String par                = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}*/
/*      case 6: {
			String par                = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new MissingParentheses().countCorrections( par ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


