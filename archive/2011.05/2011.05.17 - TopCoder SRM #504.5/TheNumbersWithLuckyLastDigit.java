import java.util.Arrays;

public class TheNumbersWithLuckyLastDigit {
	public int find(int n) {
		int[] result = new int[10];
		Arrays.fill(result, Integer.MAX_VALUE);
		int[] value = new int[10];
		result[4] = 4;
		value[4] = 1;
		result[7] = 7;
		value[7] = 1;
		while (true) {
			boolean updated = false;
			for (int j = 0; j < 10; j++) {
				if (result[j] == Integer.MAX_VALUE)
					continue;
				for (int k = j; k < 10; k++) {
					if (result[k] == Integer.MAX_VALUE)
						continue;
					if (result[(j + k) % 10] > result[j] + result[k]) {
						result[(j + k) % 10] = result[j] + result[k];
						value[(j + k) % 10] = value[j] + value[k];
						updated = true;
					}
				}
			}
			if (!updated)
				break;
		}
		if (result[n % 10] > n)
			return -1;
		return value[n % 10];
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			TheNumbersWithLuckyLastDigitHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				TheNumbersWithLuckyLastDigitHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheNumbersWithLuckyLastDigitHarness {
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
			int n                     = 99;
			int expected__            = 4;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}
		case 1: {
			int n                     = 11;
			int expected__            = 2;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}
		case 2: {
			int n                     = 13;
			int expected__            = -1;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}
		case 3: {
			int n                     = 1234567;
			int expected__            = 1;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}

		// custom cases

/*      case 4: {
			int n                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}*/
/*      case 5: {
			int n                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}*/
/*      case 6: {
			int n                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new TheNumbersWithLuckyLastDigit().find( n ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


