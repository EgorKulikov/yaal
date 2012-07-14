import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RangeSquaredSubsets {
	public long countSubsets(int nlow, int nhigh, int[] x, int[] y) {
		int[] xs = new int[x.length + 2];
		System.arraycopy(x, 0, xs, 0, x.length);
		xs[x.length] = Integer.MIN_VALUE / 3;
		xs[x.length + 1] = Integer.MAX_VALUE / 3;
		int[] ys = new int[y.length + 2];
		System.arraycopy(y, 0, ys, 0, y.length);
		ys[y.length] = Integer.MIN_VALUE / 3;
		ys[y.length + 1] = Integer.MAX_VALUE / 3;
		Arrays.sort(xs);
		Arrays.sort(ys);
		Set<Long> result = new HashSet<Long>();
		for (int i = 0; i < xs.length; i++) {
			for (int j = i + 1; j < xs.length; j++) {
				if (xs[j] - xs[i] <= nlow)
					continue;
				for (int k = 0; k < ys.length; k++) {
					for (int l = k + 1; l < ys.length; l++) {
						if (ys[l] - ys[k] <= nlow)
							continue;
						long mask = 0;
						int minX = Integer.MAX_VALUE;
						int maxX = Integer.MIN_VALUE;
						int minY = Integer.MAX_VALUE;
						int maxY = Integer.MIN_VALUE;
						for (int m = 0; m < x.length; m++) {
							if (x[m] > xs[i] && x[m] < xs[j] && y[m] > ys[k] && y[m] < ys[l]) {
								mask += 1L << m;
								minX = Math.min(minX, x[m]);
								maxX = Math.max(maxX, x[m]);
								minY = Math.min(minY, y[m]);
								maxY = Math.max(maxY, y[m]);
							}
						}
						if (mask == 0)
							continue;
						int max = Math.max(maxX - minX, maxY - minY);
						if (max > nhigh || xs[j] - xs[i] <= max || ys[l] - ys[k] <= max)
							continue;
						result.add(mask);
					}
				}
			}
		}
		return result.size();
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			RangeSquaredSubsetsHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				RangeSquaredSubsetsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RangeSquaredSubsetsHarness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase( int casenum, long expected, long received ) { 
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
			int nlow                  = 5;
			int nhigh                 = 5;
			int[] x                   = {-5,0,5};
			int[] y                   = {0,0,0};
			long expected__           = 5L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}
		case 1: {
			int nlow                  = 10;
			int nhigh                 = 10;
			int[] x                   = {-5,0,5};
			int[] y                   = {0,0,0};
			long expected__           = 5L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}
		case 2: {
			int nlow                  = 1;
			int nhigh                 = 100;
			int[] x                   = {-5,0,5};
			int[] y                   = {0,0,0};
			long expected__           = 6L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}
		case 3: {
			int nlow                  = 3;
			int nhigh                 = 100000000;
			int[] x                   = {-1,-1,-1,0,1,1,1};
			int[] y                   = {-1,0,1,1,-1,0,1};
			long expected__           = 21L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}
		case 4: {
			int nlow                  = 64;
			int nhigh                 = 108;
			int[] x                   = {-56,-234,12,324,-12,53,0,234,1,12,72};
			int[] y                   = {6,34,2,235,234,234,342,324,234,234,234};
			long expected__           = 26L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}

		// custom cases

/*      case 5: {
			int nlow                  = ;
			int nhigh                 = ;
			int[] x                   = ;
			int[] y                   = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}*/
/*      case 6: {
			int nlow                  = ;
			int nhigh                 = ;
			int[] x                   = ;
			int[] y                   = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}*/
/*      case 7: {
			int nlow                  = ;
			int nhigh                 = ;
			int[] x                   = ;
			int[] y                   = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new RangeSquaredSubsets().countSubsets( nlow, nhigh, x, y ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


