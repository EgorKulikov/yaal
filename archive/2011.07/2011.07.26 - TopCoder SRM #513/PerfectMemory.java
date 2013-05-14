import java.util.Arrays;

public class PerfectMemory {
	public double getExpectation(int N, int M) {
		int count = N * M;
		double[][] expected = new double[count + 1][count + 1];
		for (double[] row : expected)
			Arrays.fill(row, -1);
		return go(count, 0, expected);
	}

	private double go(int unknown, int known, double[][] expected) {
		if (expected[unknown][known] != -1)
			return expected[unknown][known];
		if (unknown == 0)
			return expected[unknown][known] = 0;
		double knownProbability = (double)known / unknown;
		return expected[unknown][known] = 1 + (known == 0 ? 0 : knownProbability * go(unknown - 1, known - 1, expected)) +
			(known == unknown ? 0 : (1 - knownProbability) * (
			1. / (unknown - 1) * go(unknown - 2, known, expected) +
			(double)known / (unknown - 1) * (1 + go(unknown - 2, known, expected)) +
			(unknown != known + 2 ? (double)(unknown - known - 2) / (unknown - 1) * go(unknown - 2, known + 2, expected) : 0)));
	}

	// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			PerfectMemoryHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				PerfectMemoryHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PerfectMemoryHarness {
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
			int N                     = 1;
			int M                     = 2;
			double expected__         = 1.0;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}
		case 1: {
			int N                     = 2;
			int M                     = 2;
			double expected__         = 2.6666666666666665;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}
		case 2: {
			int N                     = 2;
			int M                     = 3;
			double expected__         = 4.333333333333334;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}
		case 3: {
			int N                     = 4;
			int M                     = 4;
			double expected__         = 12.392984792984793;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}

		// custom cases

/*      case 4: {
			int N                     = ;
			int M                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}*/
/*      case 5: {
			int N                     = ;
			int M                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}*/
/*      case 6: {
			int N                     = ;
			int M                     = ;
			double expected__         = ;

			return verifyCase( casenum, expected__, new PerfectMemory().getExpectation( N, M ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


