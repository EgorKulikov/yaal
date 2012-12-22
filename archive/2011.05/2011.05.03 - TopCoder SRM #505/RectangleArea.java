import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;

public class RectangleArea {
	public int minimumQueries(String[] known) {
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(known.length + known[0].length());
		for (int i = 0; i < known.length; i++) {
			for (int j = 0; j < known[i].length(); j++) {
				if (known[i].charAt(j) == 'N')
					continue;
				setSystem.join(i, known.length + j);
			}
		}
		return setSystem.getSetCount() - 1;
	}

// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			RectangleAreaHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				RectangleAreaHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RectangleAreaHarness {
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
			String[] known            = {"NN",  "NN"};
			int expected__            = 3;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}
		case 1: {
			String[] known            = {"YNY",  "NYN",  "YNY"};
			int expected__            = 1;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}
		case 2: {
			String[] known            = {"YY",  "YY",  "YY",  "YY"};
			int expected__            = 0;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}
		case 3: {
			String[] known            = {"NNNNNNNNNN"};
			int expected__            = 10;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}
		case 4: {
			String[] known            = {"NNYYYNN",  "NNNNNYN",  "YYNNNNY",  "NNNYNNN",  "YYNNNNY"};
			int expected__            = 2;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}

		// custom cases

/*      case 5: {
			String[] known            = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}*/
/*      case 6: {
			String[] known            = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}*/
/*      case 7: {
			String[] known            = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new RectangleArea().minimumQueries(known));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


