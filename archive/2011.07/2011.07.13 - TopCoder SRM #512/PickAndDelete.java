import net.egork.string.StringUtils;

import java.util.Arrays;

public class PickAndDelete {
	private static final long MOD = 1000000007;

	public int count(String[] S) {
		String total = StringUtils.unite(S);
		String[] tokens = total.split(" ");
		int[] sequence = new int[tokens.length + 1];
		for (int i = 0; i < tokens.length; i++)
			sequence[i] = Integer.parseInt(tokens[i]);
		Arrays.sort(sequence);
		long[][] result = new long[sequence.length][sequence.length];
		result[0][0] = 1;
		long[][] c = new long[sequence.length][sequence.length];
		for (int i = 0; i < sequence.length; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
		}
		long[][] count = new long[sequence.length][sequence.length];
		for (int i = 1; i < sequence.length; i++) {
			int delta = sequence[i] - sequence[i - 1];
			count[i][0] = 1;
			for (int j = 1; j < sequence.length; j++)
				count[i][j] = (count[i][j - 1] * delta) % MOD;
		}
		for (int i = 1; i < sequence.length; i++) {
			for (int j = i; j < sequence.length; j++) {
				for (int k = 0; k <= j; k++)
					result[i][j] += result[i - 1][k] * count[i][j - k] % MOD * c[sequence.length - 1 - k][j - k] % MOD;
				result[i][j] %= MOD;
			}
		}
		return (int) result[sequence.length - 1][sequence.length - 1];
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			PickAndDeleteHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PickAndDeleteHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PickAndDeleteHarness {
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
			String[] S                = {"1 2"};
			int expected__            = 3;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}
		case 1: {
			String[] S                = {"2 2 2 2 2 2 2 2 2"};
			int expected__            = 512;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}
		case 2: {
			String[] S                = {"5", " 1 ", "2"};
			int expected__            = 34;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}
		case 3: {
			String[] S                = {"3 ", "14159 265", "3589 7", " 932"};
			int expected__            = 353127147;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}

		// custom cases

/*      case 4: {
			String[] S                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}*/
/*      case 5: {
			String[] S                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}*/
/*      case 6: {
			String[] S                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new PickAndDelete().count(S));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


