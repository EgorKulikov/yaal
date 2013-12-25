import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;

import java.util.List;

public class DivideAndShift {
	public int getLeast(int N, int M) {
		int answer = Integer.MAX_VALUE;
		for (long divisor : IntegerUtils.getDivisors(N)) {
			List<Pair<Long, Integer>> factorization = IntegerUtils.factorize(divisor);
			int power = 0;
			for (Pair<Long, Integer> factor : factorization)
				power += factor.second;
			long index = (M - 1) % (N / divisor);
			answer = (int) Math.min(answer, power + Math.min(index, N / divisor - index));
		}
		return answer;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			DivideAndShiftHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				DivideAndShiftHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DivideAndShiftHarness {
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
			int N                     = 56;
			int M                     = 14;
			int expected__            = 3;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}
		case 1: {
			int N                     = 49;
			int M                     = 5;
			int expected__            = 2;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}
		case 2: {
			int N                     = 256;
			int M                     = 7;
			int expected__            = 6;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}
		case 3: {
			int N                     = 6;
			int M                     = 1;
			int expected__            = 0;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}
		case 4: {
			int N                     = 77777;
			int M                     = 11111;
			int expected__            = 2;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			int M                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}*/
/*      case 6: {
			int N                     = ;
			int M                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}*/
/*      case 7: {
			int N                     = ;
			int M                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new DivideAndShift().getLeast( N, M ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


