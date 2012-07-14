import java.util.Arrays;

public class SubFibonacci {
	private long[] firstC;
	private long[] secondC;

	public int maxElements(int[] S) {
		firstC = new long[50];
		secondC = new long[50];
		firstC[0] = 1;
		for (int i = 2; i < 50; i++)
			firstC[i] = firstC[i - 1] + firstC[i - 2];
		secondC[1] = 1;
		for (int i = 2; i < 50; i++)
			secondC[i] = secondC[i - 1] + secondC[i - 2];
		int[] isPresent = new int[100000000 / 8 + 1];
		for (int number : S)
			isPresent[number >> 5] += 1 << (number & 31);
		Arrays.sort(S);
		int result = 0;
		for (int i = 0; i <= S.length; i++)
			result = Math.max(result, count(S, 0, i, isPresent) + count(S, i, S.length, isPresent));
		return result;
	}

	private int count(int[] array, int from, int to, int[] present) {
		if (to - from <= 2)
			return to - from;
		int result = 2;
		for (int i = from; i < to; i++) {
			for (int j = i + 1; j < to; j++) {
				for (int k = 1; k < 42; k++) {
					int current = find(array[i], array[j], k, array[to - 1]);
					if (current == -1)
						continue;
					int last = array[i];
					int countGood = 0;
					if (current != last)
						countGood++;
					while (current <= array[to - 1]) {
						if (isPresent(current, present) && current >= array[from] && current >= last)
							countGood++;
						int next = last + current;
						last = current;
						current = next;
					}
					result = Math.max(result, countGood);
				}
			}
		}
		return result;
	}

	private int find(int first, int last, int gap, int max) {
		long result = last - firstC[gap] * first;
		if (result <= 0 || result % secondC[gap] != 0)
			return -1;
		int answer = (int) (result / secondC[gap]);
		if (answer > max)
			return -1;
		return answer;
	}

	private boolean isPresent(int current, int[] present) {
		return ((present[current >> 5] >> (current & 31)) & 1) == 1;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			SubFibonacciHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SubFibonacciHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SubFibonacciHarness {
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
			int[] S                   = {8, 1, 20, 3, 10};
			int expected__            = 5;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
		case 1: {
			int[] S                   = {19, 47, 50, 58, 77, 99};
			int expected__            = 4;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
		case 2: {
			int[] S                   = {512};
			int expected__            = 1;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
		case 3: {
			int[] S                   = {3, 5, 7, 10, 13, 15, 20, 90};
			int expected__            = 7;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
		case 4: {
			int[] S                   = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
			int expected__            = 10;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}

		// custom cases
      case 5: {
			int[] S                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
			int expected__            = 11;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
      case 6: {
			int[] S                   = {1001, 1000, 2001, 3001, 11111, 22222, 33333, 55555};
			int expected__            = 7;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}
/*      case 7: {
			int[] S                   = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new SubFibonacci().maxElements(S));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


