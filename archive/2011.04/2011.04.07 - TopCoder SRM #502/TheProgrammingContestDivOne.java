package April2011.TopCoderSRM502;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.Rational;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TheProgrammingContestDivOne {
	public int find(int T, int[] maxPoints, int[] pointsPerMinute, int[] requiredTime) {
		int n = maxPoints.length;
		final Rational[] ratios = new Rational[n];
		for (int i = 0; i < n; i++)
			ratios[i] = new Rational(requiredTime[i], pointsPerMinute[i]);
		Integer[] order = ArrayUtils.generateOrder(n);
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return ratios[o1].compareTo(ratios[o2]);
			}
		});
		int[] result = new int[T + 1];
		for (int i : order) {
			for (int j = T; j >= requiredTime[i]; j--) {
				if (maxPoints[i] / j >= pointsPerMinute[i])
					result[j] = Math.max(result[j], result[j - requiredTime[i]] + maxPoints[i] - j * pointsPerMinute[i]);
			}
		}
		int answer = 0;
		for (int i = 0; i <= T; i++)
			answer = Math.max(answer, result[i]);
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			TheProgrammingContestDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheProgrammingContestDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheProgrammingContestDivOneHarness {
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
			int T                     = 74;
			int[] maxPoints           = {502};
			int[] pointsPerMinute     = {2};
			int[] requiredTime        = {47};
			int expected__            = 408;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}
		case 1: {
			int T                     = 40000;
			int[] maxPoints           = {100000, 100000};
			int[] pointsPerMinute     = {1, 100000};
			int[] requiredTime        = {50000, 30000};
			int expected__            = 0;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}
		case 2: {
			int T                     = 75;
			int[] maxPoints           = {250, 500, 1000};
			int[] pointsPerMinute     = {2, 4, 8};
			int[] requiredTime        = {25, 25, 25};
			int expected__            = 1200;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}
		case 3: {
			int T                     = 30;
			int[] maxPoints           = {100, 100, 100000};
			int[] pointsPerMinute     = {1, 1, 100};
			int[] requiredTime        = {15, 15, 30};
			int expected__            = 97000;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}

		// custom cases

/*      case 4: {
			int T                     = ;
			int[] maxPoints           = ;
			int[] pointsPerMinute     = ;
			int[] requiredTime        = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}*/
/*      case 5: {
			int T                     = ;
			int[] maxPoints           = ;
			int[] pointsPerMinute     = ;
			int[] requiredTime        = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}*/
/*      case 6: {
			int T                     = ;
			int[] maxPoints           = ;
			int[] pointsPerMinute     = ;
			int[] requiredTime        = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new TheProgrammingContestDivOne().find(T, maxPoints, pointsPerMinute, requiredTime));
		}*/
		default:
			return -1;
		}
	}
}