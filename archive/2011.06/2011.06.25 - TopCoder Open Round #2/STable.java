import java.util.Arrays;

public class STable {
	private static final long INF = Long.MAX_VALUE / 2;

	public String getString(String s, String t, long pos) {
		int n = s.length() + 1;
		int m = t.length() + 1;
		long[][] length = new long[n][m];
		Arrays.fill(length[0], 1);
		for (int i = 1; i < n; i++)
			length[i][0] = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++)
				length[i][j] = length[i - 1][j] + length[i][j - 1];
		}
		int[][][][] compare = new int[n][m][n][m];
		StringBuilder answer = new StringBuilder(50);
		for (long i = pos; i < pos + 50 && i < length[n - 1][m - 1]; i++)
			answer.append(get(n - 1, m - 1, i, length, compare, s, t));
		return answer.toString();
	}

	private char get(int row, int column, long pos, long[][] length, int[][][][] compare, String s, String t) {
		if (row == 0)
			return t.charAt(column - 1);
		if (column == 0)
			return s.charAt(row - 1);
		if (compare(row - 1, column, row, column - 1, compare, s, t) < 0) {
			if (pos < length[row - 1][column])
				return get(row - 1, column, pos, length, compare, s, t);
			else
				return get(row, column - 1, pos - length[row - 1][column], length, compare, s, t);
		} else {
			if (pos < length[row][column - 1])
				return get(row, column - 1, pos, length, compare, s, t);
			else
				return get(row - 1, column, pos - length[row][column - 1], length, compare, s, t);
		}
	}

	private int compare(int row, int column, int row2, int column2, int[][][][] compare, String s, String t) {
		if (row == row2 && column == column2)
			return 0;
		if (compare[row][column][row2][column2] != 0)
			return compare[row][column][row2][column2];
		if (row == 0 || column == 0) {
			if (row2 == 0 || column2 == 0) {
				char ch = row == 0 ? t.charAt(column - 1) : s.charAt(row - 1);
				char ch2 = row2 == 0 ? t.charAt(column2 - 1) : s.charAt(row2 - 1);
				if (ch < ch2)
					return compare[row][column][row2][column2] = -1;
				return compare[row][column][row2][column2] = 1;
			}
			if (compare(row2 - 1, column2, row2, column2 - 1, compare, s, t) == -1)
				return compare[row][column][row2][column2] = compare(row, column, row2 - 1, column2, compare, s, t);
			else
				return compare[row][column][row2][column2] = compare(row, column, row2, column2 - 1, compare, s, t);
		}
		if (row2 == 0 || column2 == 0) {
			if (compare(row - 1, column, row, column - 1, compare, s, t) == -1)
				return compare[row][column][row2][column2] = compare(row - 1, column, row2, column2, compare, s, t);
			else
				return compare[row][column][row2][column2] = compare(row, column - 1, row2, column2, compare, s, t);
		}
		if (compare(row - 1, column, row, column - 1, compare, s, t) == -1) {
			if (compare(row2 - 1, column2, row2, column2 - 1, compare, s, t) == -1) {
				int result = compare(row - 1, column, row2 - 1, column2, compare, s, t);
				if (result != 0)
					return compare[row][column][row2][column2] = result;
				return compare[row][column][row2][column2] = compare(row, column - 1, row2, column2 - 1, compare, s, t);
			} else {
				int result = compare(row - 1, column, row2, column2 - 1, compare, s, t);
				if (result != 0)
					return compare[row][column][row2][column2] = result;
				return compare[row][column][row2][column2] = compare(row, column - 1, row2 - 1, column2, compare, s, t);
			}
		} else {
			if (compare(row2 - 1, column2, row2, column2 - 1, compare, s, t) == -1) {
				int result = compare(row, column - 1, row2 - 1, column2, compare, s, t);
				if (result != 0)
					return compare[row][column][row2][column2] = result;
				return compare[row][column][row2][column2] = compare(row - 1, column, row2, column2 - 1, compare, s, t);
			} else {
				int result = compare(row, column - 1, row2, column2 - 1, compare, s, t);
				if (result != 0)
					return compare[row][column][row2][column2] = result;
				return compare[row][column][row2][column2] = compare(row - 1, column, row2 - 1, column2, compare, s, t);
			}
		}
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			STableHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				STableHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class STableHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			String s                  = "ad";
			String t                  = "cb";
			long pos                  = 0L;
			String expected__         = "acbacd";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}
		case 1: {
			String s                  = "fox";
			String t                  = "cat";
			long pos                  = 0L;
			String expected__         = "acfcfoacftacfcfocfox";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}
		case 2: {
			String s                  = "Ra6b1t";
			String t                  = "W0lf";
			long pos                  = 66L;
			String expected__         = "RWab0RWRWa0RWl0RWRWa6RWa0RWRWa6RWa6RWab0RWRWa6RWa6";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}
		case 3: {
			String s                  = "M0HAXG";
			String t                  = "COFU12";
			long pos                  = 919L;
			String expected__         = "MOFU2";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}
		case 4: {
			String s                  = "a0B1c2D3e4F5gHiJkLmN";
			String t                  = "A9b8C7d6EfGhIjKlMn";
			long pos                  = 9876543210L;
			String expected__         = "B10AaB1c0Aa9Aa0AaB0AaB10AaB1c0AaB1c20Aa9Aa0AaB0Aa9";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}
		case 5: {
			String s                  = "TCOR2";
			String t                  = "MEDiUm";
			long pos                  = 350L;
			String expected__         = "MTDEMTiCMTEMTDEMTDEMTiDEMTiUCMTEMTCMTOCMTEMTDEMTCM";

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}

		// custom cases

/*      case 6: {
			String s                  = ;
			String t                  = ;
			long pos                  = L;
			String expected__         = ;

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}*/
/*      case 7: {
			String s                  = ;
			String t                  = ;
			long pos                  = L;
			String expected__         = ;

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}*/
/*      case 8: {
			String s                  = ;
			String t                  = ;
			long pos                  = L;
			String expected__         = ;

			return verifyCase(casenum, expected__, new STable().getString(s, t, pos));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


