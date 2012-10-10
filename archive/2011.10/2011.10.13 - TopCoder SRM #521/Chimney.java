import net.egork.numbers.Matrix;

import java.util.Arrays;

public class Chimney {
	public int countWays(long n) {
		int[] validStates = new int[35];
		int[] index = new int[1 << 12];
		Arrays.fill(index, -1);
		int count = 0;
		for (int i = 0; i < (1 << 12); i++) {
			if ((i & 15) == 15)
				continue;
			boolean isValid = true;
			for (int j = 4; j < 12; j++) {
				if ((i >> j & 1) != 0) {
					int i1 = j - 4;
					int i2 = j - 3;
					if (i2 / 4 == j / 4)
						i2 -= 4;
					if ((i >> i1 & 1) == 0 || (i >> i2 & 1) == 0)
						isValid = false;
				}
			}
			if (isValid) {
				index[i] = count;
				validStates[count++] = i;
			}
		}
		long[][] matrix = new long[35][35];
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 12; j++) {
				if ((validStates[i] >> j & 1) != 0)
					continue;
				int newState = validStates[i] + (1 << j);
				if ((newState & 15) == 15)
					newState >>= 4;
				if (index[newState] != -1)
					matrix[i][index[newState]]++;
			}
		}
		Matrix m = new Matrix(35, 35);
		for (int i = 0; i < 35; i++)
			System.arraycopy(matrix[i], 0, m.data[i], 0, 35);
		Matrix.mod = 1000000007;
		m = m.power(n * 4);
		return (int) m.data[0][0];
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			ChimneyHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				ChimneyHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ChimneyHarness {
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
			long n                    = 1L;
			int expected__            = 24;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}
		case 1: {
			long n                    = 2L;
			int expected__            = 1088;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}
		case 2: {
			long n                    = 5L;
			int expected__            = 110198784;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}
		case 3: {
			long n                    = 6L;
			int expected__            = 138284509;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}
		case 4: {
			long n                    = 100000L;
			int expected__            = 19900327;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}

		// custom cases

/*      case 5: {
			long n                    = L;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}*/
/*      case 6: {
			long n                    = L;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}*/
/*      case 7: {
			long n                    = L;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Chimney().countWays( n ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


