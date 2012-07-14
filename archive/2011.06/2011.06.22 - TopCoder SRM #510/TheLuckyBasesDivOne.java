import java.util.Arrays;

public class TheLuckyBasesDivOne {
	public long find(long n) {
		long[] luckyNumber = new long[(1 << 17) - 2];
		luckyNumber[0] = 4;
		luckyNumber[1] = 7;
		int start = 0;
		int end = 2;
		for (int i = 1; i < 16; i++) {
			for (int j = start; j < end; j++) {
				luckyNumber[2 * j - 2 * start + end] = luckyNumber[j] * 10 + 4;
				luckyNumber[2 * j - 2 * start + end + 1] = luckyNumber[j] * 10 + 7;
			}
			int nextStart = end;
			end += 2 * (end - start);
			start = nextStart;
		}
		if (Arrays.binarySearch(luckyNumber, n) >= 0)
			return -1;
		boolean[] low = new boolean[50000000];
		for (long lucky : luckyNumber) {
			if (lucky < 50000000)
				low[((int) lucky)] = true;
			else
				break;
		}
		long answer = 0;
		long max = (long) (Math.sqrt(n / 44) - 3);
		while (max * max * 44 <= n)
			max++;
		max--;
		answer += check(5, max, n, low);
		max = (long) (Math.sqrt(n / 8) - 3);
		while (max * max * 8 < n)
			max++;
		max--;
		long from = max;
		max = (long) (Math.sqrt(n / 7) - 3);
		while (max * max * 7 <= n)
			max++;
		answer += check(from, max, n, low);
		max = (long) (Math.sqrt(n / 5) - 3);
		while (max * max * 5 < n)
			max++;
		max--;
		from = max;
		max = (long) (Math.sqrt(n / 4) - 3);
		while (max * max * 4 <= n)
			max++;
		answer += check(from, max, n, low);
		for (long lucky : luckyNumber) {
			long nCopy = n - lucky;
			for (long lucky2 : luckyNumber) {
				if (lucky2 * lucky2 > nCopy)
					break;
				if (nCopy % lucky2 == 0 && n / lucky2 > lucky)
					answer++;
			}
		}
		return answer;
	}

	private long check(long from, long to, long n, boolean[] low) {
		long answer;
		if (to < from)
			return 0;
		answer = to - from + 1;
		for (long base = from; base <= to; base++) {
			long nCopy = n;
			do {
				if (!low[((int) (nCopy % base))]) {
					answer--;
					break;
				}
				nCopy /= base;
			} while (nCopy != 0);
		}
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			TheLuckyBasesDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheLuckyBasesDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheLuckyBasesDivOneHarness {
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
			long n                    = 255L;
			long expected__           = 2L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}
		case 1: {
			long n                    = 474L;
			long expected__           = -1L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}
		case 2: {
			long n                    = 13L;
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}
		case 3: {
			long n                    = 4748L;
			long expected__           = 5L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}

		// custom cases

      case 4: {
			long n                    = 10000000000000000L;
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}
/*      case 5: {
			long n                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}*/
/*      case 6: {
			long n                    = L;
			long expected__           = L;

			return verifyCase(casenum, expected__, new TheLuckyBasesDivOne().find(n));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


