import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class CubeBuilding {
	private static final int MOD = 1000000007;

	public int getCount(int R, int G, int B, int N) {
		long[][] c = IntegerUtils.generateBinomialCoefficients(50);
		long[][] row = calculateRow(25, 50, N);
		long first = c[G + B][G] % MOD;
		long second = c[R + B][R] % MOD;
		long third = c[R + G][R] % MOD;
		long[][] result = count(25, 50, N, row);
		return (int) ((result[R][G + B] * first + result[G][R + B] * second + result[B][G + R] * third) % MOD);
	}

	private long[][] count(int good, int bad, int size, long[][] row) {
//		long[][] row = calculateRow(good, bad, size);
		long[][][] result = new long[size + 1][good + 1][bad + 1];
		result[0][0][0] = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= good; j++) {
				for (int k = 0; k <= bad; k++) {
					for (int l = 0; l <= j; l++) {
						for (int m = 0; m <= k; m++)
							result[i + 1][j][k] += result[i][l][m] * row[j - l][k - m] % MOD;
					}
					result[i + 1][j][k] %= MOD;
				}
			}
		}
		return result[size];
	}

	private long[][] calculateRow(int good, int bad, int size) {
//		long time = System.currentTimeMillis();
		long[] result = new long[(good + 1) * (bad + 1) * (good + 1) * (good + 1)];
		long[] nextResult = new long[(good + 1) * (bad + 1) * (good + 1) * (good + 1)];
		result[0] = 1;
		int first = (bad + 1) * (good + 1) * (good + 1);
		int second = (bad + 1) * (good + 1);
		int third = good + 1;
		for (int i = 0; i < size; i++) {
			Arrays.fill(nextResult, 0);
			for (int j = 0; j <= good; j++) {
				for (int k = 0; k <= bad; k++) {
					for (int l = 0; l <= j; l++) {
						int index = j * second + k * third + l;
						for (int m = 0; m <= l; m++)
							nextResult[index] += result[index + m * first];
						nextResult[index] %= MOD;
					}
				}
			}
			for (int h = 1; h <= good; h++) {
				for (int j = h; j <= good; j++) {
					for (int k = 0; k <= bad; k++)
						nextResult[j * second + k * third + h + h * first] += nextResult[(j - 1) * second + k * third + (h - 1) + (h - 1) * first];
				}
				for (int h1 = h; h1 <= good; h1++) {
					for (int j = h1; j <= good; j++) {
						for (int k = 0; k <= bad; k++) {
							int index = j * second + k * third + h1 + h * first;
							nextResult[index] += nextResult[(j - 1) * second + k * third + h1 + (h - 1) * first];
							if (k != 0)
								nextResult[index] += nextResult[j * second + (k - 1) * third + h1 + (h - 1) * first];
//							nextResult[j][k][h1][h] %= MOD;
						}
					}
				}
			}
			long[] temp = result;
			result = nextResult;
			nextResult = temp;
		}
		long[][] totalResult = new long[good + 1][bad + 1];
		for (int i = 0; i <= good; i++) {
			for (int j = 0; j <= bad; j++) {
				int index = i * second + j * third;
				for (int h = 0; h <= good; h++) {
					for (int h1 = h; h1 <= good; h1++)
						totalResult[i][j] += result[index + h * first + h1];
				}
				totalResult[i][j] %= MOD;
			}
		}
//		System.err.println(System.currentTimeMillis() - time);
		return totalResult;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			CubeBuildingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CubeBuildingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CubeBuildingHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum) {
		switch(casenum) {
		case 0: {
			int R                     = 1;
			int G                     = 0;
			int B                     = 1;
			int N                     = 2;
			int expected__            = 4;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}
		case 1: {
			int R                     = 1;
			int G                     = 1;
			int B                     = 2;
			int N                     = 1;
			int expected__            = 0;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}
		case 2: {
			int R                     = 2;
			int G                     = 2;
			int B                     = 1;
			int N                     = 3;
			int expected__            = 162;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}
		case 3: {
			int R                     = 0;
			int G                     = 0;
			int B                     = 10;
			int N                     = 12;
			int expected__            = 372185933;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}

		// custom cases

      case 4: {
			int R                     = 25;
			int G                     = 25;
			int B                     = 25;
			int N                     = 25;
			int expected__            = 0;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}
/*      case 5: {
			int R                     = ;
			int G                     = ;
			int B                     = ;
			int N                     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}*/
/*      case 6: {
			int R                     = ;
			int G                     = ;
			int B                     = ;
			int N                     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CubeBuilding().getCount(R, G, B, N));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


