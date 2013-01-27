import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class Reflections {
	private static long[][] c = IntegerUtils.generateBinomialCoefficients(20);

	public long minimumMoves(int[] mirrorX, int[] mirrorY, int[] mirrorZ, int[] finalPosition) {
		return go(mirrorX, finalPosition[0]) + go(mirrorY, finalPosition[1]) + go(mirrorZ, finalPosition[2]);
	}

	private long go(int[] mirrors, int destination) {
		int[] firstHalf = new int[mirrors.length / 2];
		int[] secondHalf = new int[mirrors.length - firstHalf.length];
		System.arraycopy(mirrors, 0, firstHalf, 0, firstHalf.length);
		System.arraycopy(mirrors, firstHalf.length, secondHalf, 0, secondHalf.length);
		long[][][] firstResult = go(firstHalf);
		long[][][] secondResult = go(secondHalf);
		long result = Long.MAX_VALUE;
		for (long[][] row : firstResult) {
			for (long[] column : row)
				Arrays.sort(column);
		}
		for (long[][] row : secondResult) {
			for (long[] column : row)
				Arrays.sort(column);
		}
		for (int i = 0; i <= firstHalf.length; i++) {
			for (int j = 0; i + j <= firstHalf.length; j++) {
				for (int k = 0; k <= secondHalf.length; k++) {
					for (int l = 0; k + l <= secondHalf.length; l++) {
						if (i - j + k - l < 0 || i - j + k - l > 1)
							continue;
						long[] first = firstResult[i][j];
						long[] second = secondResult[k][l];
						int bonus = i + j + k + l;
						for (long firstDistance : first) {
							int index = Arrays.binarySearch(second, destination - firstDistance);
							if (index < 0)
								index = - index - 2;
							for (int m = Math.max(index, 0); m <= Math.min(index + 1, second.length - 1); m++)
								result = Math.min(result, Math.abs(firstDistance + second[m] - destination) + bonus);
						}
					}
				}
			}
		}
		return result;
	}

	private long[][][] go(int[] mirrors) {
		long[][][] result = new long[mirrors.length + 1][][];
		int[][] index = new int[mirrors.length + 1][];
		int[][] nextIndex = new int[mirrors.length + 1][];
		for (int i = 0; i <= mirrors.length; i++) {
			result[i] = new long[mirrors.length - i + 1][];
			index[i] = new int[mirrors.length - i + 1];
			nextIndex[i] = new int[mirrors.length - i + 1];
			for (int j = 0; j < result[i].length; j++)
				result[i][j] = new long[(int) (c[mirrors.length][i] * c[mirrors.length - i][j])];
		}
		nextIndex[0][0] = 1;
		for (int i = 0; i < mirrors.length; i++) {
			for (int j = 0; j < index.length; j++)
				System.arraycopy(nextIndex[j], 0, index[j], 0, index[j].length);
			for (int j = 0; j < result.length; j++) {
				for (int k = 0; k < result[j].length; k++) {
					for (int l = 0; l < index[j][k]; l++) {
						result[j + 1][k][nextIndex[j + 1][k]++] = 2 * mirrors[i] + result[j][k][l];
						result[j][k + 1][nextIndex[j][k + 1]++] = -2 * mirrors[i] + result[j][k][l];
					}
				}
			}
		}
		return result;
	}

	// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			ReflectionsHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				ReflectionsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ReflectionsHarness {
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
			int[] mirrorX             = {2};
			int[] mirrorY             = {};
			int[] mirrorZ             = {};
			int[] finalPosition       = {3, 0, 1};
			long expected__           = 3L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}
		case 1: {
			int[] mirrorX             = {-5, 1, 4, 2, 3, 6, -6};
			int[] mirrorY             = {};
			int[] mirrorZ             = {};
			int[] finalPosition       = {9, 0, 0};
			long expected__           = 2L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}
		case 2: {
			int[] mirrorX             = {5, 8};
			int[] mirrorY             = {};
			int[] mirrorZ             = {};
			int[] finalPosition       = {4, 0, 0};
			long expected__           = 4L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}
		case 3: {
			int[] mirrorX             = {5};
			int[] mirrorY             = {5};
			int[] mirrorZ             = {1, 2, 3};
			int[] finalPosition       = {10, 12, -1};
			long expected__           = 5L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}
		case 4: {
			int[] mirrorX             = {8, -3, 21};
			int[] mirrorY             = {4, 5};
			int[] mirrorZ             = {-7, -2, -1, 7, 14};
			int[] finalPosition       = {40, -4, 31};
			long expected__           = 10L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}

		// custom cases

/*      case 5: {
			int[] mirrorX             = ;
			int[] mirrorY             = ;
			int[] mirrorZ             = ;
			int[] finalPosition       = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}*/
/*      case 6: {
			int[] mirrorX             = ;
			int[] mirrorY             = ;
			int[] mirrorZ             = ;
			int[] finalPosition       = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}*/
/*      case 7: {
			int[] mirrorX             = ;
			int[] mirrorY             = ;
			int[] mirrorZ             = ;
			int[] finalPosition       = ;
			long expected__           = L;

			return verifyCase( casenum, expected__, new Reflections().minimumMoves( mirrorX, mirrorY, mirrorZ, finalPosition ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


