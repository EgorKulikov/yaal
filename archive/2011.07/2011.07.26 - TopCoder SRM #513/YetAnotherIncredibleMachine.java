import java.util.Arrays;

public class YetAnotherIncredibleMachine {
	public int countWays(int[] platformMount, int[] platformLength, int[] balls) {
		Arrays.sort(balls);
		long result = 1;
		for (int i = 0; i < platformMount.length; i++) {
			int leftEnd = platformMount[i] - platformLength[i];
			int rightEnd = platformMount[i] + platformLength[i];
			for (int x : balls) {
				if (x >= platformMount[i])
					rightEnd = Math.min(rightEnd, x - 1);
				else
					leftEnd = Math.max(leftEnd, x + 1);
			}
			result = result * Math.max(rightEnd - leftEnd - platformLength[i] + 1, 0) % 1000000009;
		}
		return (int) result;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			YetAnotherIncredibleMachineHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				YetAnotherIncredibleMachineHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherIncredibleMachineHarness {
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
			int[] platformMount       = {7};
			int[] platformLength      = {10};
			int[] balls               = {3,4};
			int expected__            = 3;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}
		case 1: {
			int[] platformMount       = {1,4};
			int[] platformLength      = {3,3};
			int[] balls               = {2,7};
			int expected__            = 1;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}
		case 2: {
			int[] platformMount       = {4,4,4};
			int[] platformLength      = {10,9,8};
			int[] balls               = {1,100};
			int expected__            = 27;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}
		case 3: {
			int[] platformMount       = {0};
			int[] platformLength      = {1};
			int[] balls               = {0};
			int expected__            = 0;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}
		case 4: {
			int[] platformMount       = {100, -4215, 251};
			int[] platformLength      = {400, 10000, 2121};
			int[] balls               = {5000, 2270, 8512, 6122};
			int expected__            = 250379170;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}

		// custom cases

/*      case 5: {
			int[] platformMount       = ;
			int[] platformLength      = ;
			int[] balls               = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}*/
/*      case 6: {
			int[] platformMount       = ;
			int[] platformLength      = ;
			int[] balls               = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}*/
/*      case 7: {
			int[] platformMount       = ;
			int[] platformLength      = ;
			int[] balls               = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new YetAnotherIncredibleMachine().countWays( platformMount, platformLength, balls ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


