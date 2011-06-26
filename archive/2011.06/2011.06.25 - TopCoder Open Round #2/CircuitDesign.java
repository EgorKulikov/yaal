public class CircuitDesign {
	private static final int MOD = 1000000007;

	public int countPerms(int n, int[] top, int[] bottom) {
		long[] factorial = new long[n + 1];
		factorial[0] = 1;
		for (int i = 1; i <= n; i++)
			factorial[i] = (factorial[i - 1] * i) % MOD;
		for (int i = 0; i < top.length; i++) {
			top[i]--;
			bottom[i]--;
		}
		long result = 1;
		boolean[] used = new boolean[n];
		for (int aTop : top)
			used[aTop] = true;
		int count = n;
		for (boolean cell : used) {
			if (!cell) {
				result *= count--;
				result %= MOD;
			}
		}
		used = new boolean[n];
		for (int aTop : bottom)
			used[aTop] = true;
		count = n;
		for (boolean cell : used) {
			if (!cell) {
				result *= count--;
				result %= MOD;
			}
		}
		boolean[][] connected = new boolean[2 * n][2 * n];
		for (int i = 0; i < top.length; i++)
			connected[top[i]][bottom[i] + n] = connected[bottom[i] + n][top[i]] = true;
		int[] degree = new int[2 * n];
		for (int i = 0; i < top.length; i++) {
			degree[top[i]]++;
			degree[n + bottom[i]]++;
		}
		int[] specialDegree = new int[2 * n];
		for (int i = 0; i < top.length; i++) {
			if (degree[top[i]] > 1 && degree[n + bottom[i]] > 1) {
				specialDegree[top[i]]++;
				specialDegree[bottom[i] + n]++;
			}
		}
		boolean[] visited = new boolean[2 * n];
		int componentCount = 0;
		int[] queue = new int[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			if (degree[i] > 1 && !visited[i]) {
				componentCount++;
				visited[i] = true;
				if (specialDegree[i] == 0) {
					result = (result * factorial[degree[i]]) % MOD;
					for (int j = 0; j < 2 * n; j++) {
						if (connected[i][j])
							visited[j] = true;
					}
					continue;
				}
				int ones = 0;
				int size = 1;
				queue[0] = i;
				result = result * 2 % MOD;
				for (int j = 0; j < size; j++) {
					int c = queue[j];
					if (specialDegree[c] > 2)
						return 0;
					if (specialDegree[c] == 2)
						result = result * factorial[degree[c] - 2] % MOD;
					else {
						ones++;
						result = result * factorial[degree[c] - 1] % MOD;
					}
					for (int k = 0; k < 2 * n; k++) {
						if (connected[c][k] && !visited[k]) {
							visited[k] = true;
							if (degree[k] > 1)
								queue[size++] = k;
						}
					}
				}
				if (ones == 0)
					return 0;
			}
		}
		int edges = 0;
		for (int i = 0; i < 2 * n; i++) {
			if (degree[i] == 1 && !visited[i])
				edges++;
		}
		edges /= 2;
		componentCount += edges;
		result = result * factorial[componentCount] % MOD;
		return (int) result;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			CircuitDesignHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CircuitDesignHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CircuitDesignHarness {
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
			int n                     = 4;
			int[] top                 = {1,1,2,4,4};
			int[] bottom              = {1,2,2,2,3};
			int expected__            = 32;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}
		case 1: {
			int n                     = 2;
			int[] top                 = {1,2};
			int[] bottom              = {2,1};
			int expected__            = 2;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}
		case 2: {
			int n                     = 3;
			int[] top                 = {1,1,1,2,2,2,3,3,3};
			int[] bottom              = {1,2,3,1,2,3,1,2,3};
			int expected__            = 0;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}
		case 3: {
			int n                     = 30;
			int[] top                 = {5,5,5,5,5,5,5,5,5, 5,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
			int[] bottom              = {1,2,3,4,5,6,7,8,9,10,15,15,15,15,15,15,15,15,15,15,25,25,25,25,25,25,25,25,25,25};
			int expected__            = 628573100;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}
		case 4: {
			int n                     = 5;
			int[] top                 = {1,2,3,4,5,1,2,3,4};
			int[] bottom              = {1,2,3,4,5,5,1,4,2};
			int expected__            = 2;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}

		// custom cases

/*      case 5: {
			int n                     = ;
			int[] top                 = ;
			int[] bottom              = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}*/
/*      case 6: {
			int n                     = ;
			int[] top                 = ;
			int[] bottom              = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}*/
/*      case 7: {
			int n                     = ;
			int[] top                 = ;
			int[] bottom              = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CircuitDesign().countPerms(n, top, bottom));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


