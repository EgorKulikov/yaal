import java.util.Arrays;

public class TheLuckyGameDivOne {
	public int find(long a, long b, long jLen, long bLen) {
		long[] luckyNumber = new long[2046];
		luckyNumber[0] = 4;
		luckyNumber[1] = 7;
		int start = 0;
		int end = 2;
		for (int i = 1; i < 10; i++) {
			for (int j = start; j < end; j++) {
				luckyNumber[2 * j - 2 * start + end] = luckyNumber[j] * 10 + 4;
				luckyNumber[2 * j - 2 * start + end + 1] = luckyNumber[j] * 10 + 7;
			}
			int nextStart = end;
			end += 2 * (end - start);
			start = nextStart;
		}
		Arrays.sort(luckyNumber);
		long[] candidates = new long[4096];
		int length = 0;
		candidates[length++] = a;
		if (b - a + 1 != jLen)
			candidates[length++] = b - jLen + 1;
		if (length == 2) {
			for (long lucky : luckyNumber) {
				long first = lucky - bLen + 1;
				if (first > candidates[0] && first < candidates[1])
					candidates[length++] = first;
				first = lucky + bLen - jLen;
				if (first > candidates[0] && first < candidates[1])
					candidates[length++] = first;
			}
		}
		int answer = 0;
		for (int i = 0; i < length; i++) {
			int index1 = 0;
			int index2 = 0;
			long from = candidates[i];
			long to = candidates[i] + jLen;
			while (index1 < 2046 && luckyNumber[index1] < from) {
				index1++;
				index2++;
			}
			while (index2 < 2046 && luckyNumber[index2] < from + bLen)
				index2++;
			int current = index2 - index1;
			while (index1 < 2046 && luckyNumber[index1] < to - bLen) {
				if (index2 == 2046 || luckyNumber[index2] >= to || luckyNumber[index2] - luckyNumber[index1] > bLen)
					index1++;
				else
					index2++;
				current = Math.min(current, index2 - index1);
			}
			answer = Math.max(answer, current);
		}
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			TheLuckyGameDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheLuckyGameDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheLuckyGameDivOneHarness {
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
			long a                    = 1L;
			long b                    = 10L;
			long jLen                 = 2L;
			long bLen                 = 1L;
			int expected__            = 0;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}
		case 1: {
			long a                    = 1L;
			long b                    = 100L;
			long jLen                 = 100L;
			long bLen                 = 100L;
			int expected__            = 6;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}
		case 2: {
			long a                    = 4L;
			long b                    = 8L;
			long jLen                 = 3L;
			long bLen                 = 2L;
			int expected__            = 1;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}
		case 3: {
			long a                    = 1L;
			long b                    = 100L;
			long jLen                 = 75L;
			long bLen                 = 50L;
			int expected__            = 2;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}

		// custom cases

      case 4: {
			long a                    = 1L;
			long b                    = 4747L;
			long jLen                 = 8L;
			long bLen                 = 3L;
			int expected__            = 1;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}
/*      case 5: {
			long a                    = L;
			long b                    = L;
			long jLen                 = L;
			long bLen                 = L;
			int expected__            = ;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}*/
/*      case 6: {
			long a                    = L;
			long b                    = L;
			long jLen                 = L;
			long bLen                 = L;
			int expected__            = ;

			return verifyCase(casenum, expected__, new TheLuckyGameDivOne().find(a, b, jLen, bLen));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


