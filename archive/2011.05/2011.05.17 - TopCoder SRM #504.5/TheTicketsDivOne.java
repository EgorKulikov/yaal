public class TheTicketsDivOne {
	public double find(int n, int m) {
		m--;
		double[] result = new double[n];
		double[] nextResult = new double[n];
		result[0] = 1;
		for (int i = 2; i <= n; i++) {
			nextResult[0] = 1. / 6;
			double remainingProbability = 1. / 2;
			for (int j = i - 1; j > 0; j--) {
				nextResult[0] += result[j - 1] * remainingProbability / 3;
				remainingProbability /= 2;
			}
			nextResult[0] /= 1 - remainingProbability;
			for (int j = 1; j < i; j++)
				nextResult[j] = nextResult[j - 1] / 2 + result[j - 1] / 3;
			double[] temp = result;
			result = nextResult;
			nextResult = temp;
		}
		return result[m];
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			TheTicketsDivOneHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				TheTicketsDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheTicketsDivOneHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if ( Double.isNaN( expected ) || Double.isInfinite( expected ) || Double.isNaN( result ) || Double.isInfinite( result ) || expected == 0 ) return 0; return Math.abs( result-expected ) / Math.abs( expected ); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase( int casenum, double expected, double received ) { 
		System.err.print("Example " + casenum + "... ");
		if ( compareOutput( expected, received ) ) {
			System.err.print("PASSED");
			double rerr = relativeError( expected, received );
			if ( rerr > 0 ) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			int n                     = 2;
			int m                     = 1;
			double expected__         = 0.4444444444444444;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}
		case 1: {
			int n                     = 2;
			int m                     = 2;
			double expected__         = 0.5555555555555556;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}
		case 2: {
			int n                     = 1;
			int m                     = 1;
			double expected__         = 1.0;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}
		case 3: {
			int n                     = 3;
			int m                     = 2;
			double expected__         = 0.31746031746031744;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}

		// custom cases

/*      case 4: {
			int n                     = ;
			int m                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}*/
/*      case 5: {
			int n                     = ;
			int m                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}*/
/*      case 6: {
			int n                     = ;
			int m                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new TheTicketsDivOne().find( n, m ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


