import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ManhattanPatrol {
	public long countIntersections(int N, int AX, int BX, int MX, int AY, int BY, int MY) {
		int[] x = generate(AX, BX, MX, N);
		int[] y = generate(AY, BY, MY, N);
		boolean[][] points = new boolean[N][N];
		for (int i = 0; i < N; i++)
			points[x[i]][y[i]] = true;
		int[][] count = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			int curCount = 0;
			for (int j = 1; j <= N; j++) {
				if (points[i - 1][j - 1])
					curCount++;
				count[i][j] = count[i - 1][j] + curCount;
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (x[i] >= x[j])
					continue;
				int minX = Math.min(x[i], x[j]);
				int maxX = Math.max(x[i], x[j]) + 1;
				int minY = Math.min(y[i], y[j]);
				int maxY = Math.max(y[i], y[j]);
				result += (count[minX][maxY] + count[0][minY] - count[minX][minY] - count[0][maxY]) *
					(count[N][maxY] + count[maxX][minY] - count[N][minY] - count[maxX][maxY]);
			}
		}
		return result;
	}

	private int[] generate(int ax, int bx, int mx, int n) {
		int[] x = new int[n];
		x[0] = bx;
		for (int i = 1; i < n; i++)
			x[i] = (ax * x[i - 1] + bx) % mx;
		int[] xClone = x.clone();
		Arrays.sort(xClone);
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		for (int i = 0; i < xClone.length; i++)
			index.put(xClone[i], i);
		for (int i = 0; i < x.length; i++)
			x[i] = index.get(x[i]);
		return x;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			ManhattanPatrolHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ManhattanPatrolHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ManhattanPatrolHarness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
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
			int N                     = 4;
			int AX                    = 1;
			int BX                    = 2;
			int MX                    = 11;
			int AY                    = 2;
			int BY                    = 2;
			int MY                    = 13;
			long expected__           = 1L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}
		case 1: {
			int N                     = 7;
			int AX                    = 1;
			int BX                    = 2;
			int MX                    = 11;
			int AY                    = 2;
			int BY                    = 2;
			int MY                    = 13;
			long expected__           = 2L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}
		case 2: {
			int N                     = 6;
			int AX                    = 1;
			int BX                    = 2;
			int MX                    = 7;
			int AY                    = 1;
			int BY                    = 1;
			int MY                    = 6;
			long expected__           = 5L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}
		case 3: {
			int N                     = 7;
			int AX                    = 1;
			int BX                    = 1;
			int MX                    = 11;
			int AY                    = 13;
			int BY                    = 1;
			int MY                    = 16;
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}
		case 4: {
			int N                     = 20;
			int AX                    = 6;
			int BX                    = 1;
			int MX                    = 211;
			int AY                    = 13;
			int BY                    = 11;
			int MY                    = 186;
			long expected__           = 862L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			int AX                    = ;
			int BX                    = ;
			int MX                    = ;
			int AY                    = ;
			int BY                    = ;
			int MY                    = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}*/
/*      case 6: {
			int N                     = ;
			int AX                    = ;
			int BX                    = ;
			int MX                    = ;
			int AY                    = ;
			int BY                    = ;
			int MY                    = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}*/
/*      case 7: {
			int N                     = ;
			int AX                    = ;
			int BX                    = ;
			int MX                    = ;
			int AY                    = ;
			int BY                    = ;
			int MY                    = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new ManhattanPatrol().countIntersections(N, AX, BX, MX, AY, BY, MY));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


