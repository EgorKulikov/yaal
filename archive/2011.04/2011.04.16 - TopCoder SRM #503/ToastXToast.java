package April2011.TopCoderSRM503;

import java.util.Arrays;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ToastXToast {
	public int bake(int[] undertoasted, int[] overtoasted) {
		Arrays.sort(undertoasted);
		Arrays.sort(overtoasted);
		if (undertoasted[undertoasted.length - 1] < overtoasted[0])
			return 1;
		if (undertoasted[undertoasted.length - 1] < overtoasted[overtoasted.length - 1] &&
			undertoasted[0] < overtoasted[0])
		{
			return 2;
		}
		return -1;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			ToastXToastHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ToastXToastHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ToastXToastHarness {
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
			int[] undertoasted        = {2,4};
			int[] overtoasted         = {5,6,3};
			int expected__            = 2;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}
		case 1: {
			int[] undertoasted        = {5};
			int[] overtoasted         = {4};
			int expected__            = -1;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}
		case 2: {
			int[] undertoasted        = {1,2,3};
			int[] overtoasted         = {5,6,7};
			int expected__            = 1;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}
		case 3: {
			int[] undertoasted        = {1,3,5};
			int[] overtoasted         = {2,4,6};
			int expected__            = 2;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}

		// custom cases

/*      case 4: {
			int[] undertoasted        = ;
			int[] overtoasted         = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}*/
/*      case 5: {
			int[] undertoasted        = ;
			int[] overtoasted         = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}*/
/*      case 6: {
			int[] undertoasted        = ;
			int[] overtoasted         = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ToastXToast().bake(undertoasted, overtoasted));
		}*/
		default:
			return -1;
		}
	}
}