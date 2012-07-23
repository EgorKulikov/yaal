package April2011.TopCoderSRM502;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TheLotteryBothDivs {
	public double find(String[] goodSuffixes) {
		int n = goodSuffixes.length;
		boolean[] bad = new boolean[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && (goodSuffixes[i].endsWith(goodSuffixes[j]) && (!goodSuffixes[i].equals(goodSuffixes[j]) || i > j)))
					bad[i] = true;
			}
		}
		double answer = 0;
		for (int i = 0; i < n; i++) {
			if (!bad[i])
				answer += Math.pow(0.1, goodSuffixes[i].length());
		}
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			TheLotteryBothDivsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheLotteryBothDivsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheLotteryBothDivsHarness {
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

	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }

	static String formatResult(double res) {
		return String.format("%.10g", res);
	}

	static int verifyCase(int casenum, double expected, double received) {
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			String[] goodSuffixes     = {"4"};
			double expected__         = 0.1;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}
		case 1: {
			String[] goodSuffixes     = {"4", "7"};
			double expected__         = 0.2;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}
		case 2: {
			String[] goodSuffixes     = {"47", "47"};
			double expected__         = 0.01;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}
		case 3: {
			String[] goodSuffixes     = {"47", "58", "4747", "502"};
			double expected__         = 0.021;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}
		case 4: {
			String[] goodSuffixes     = {"8542861", "1954", "6", "523", "000000000", "5426", "8"};
			double expected__         = 0.201100101;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}

		// custom cases

/*      case 5: {
			String[] goodSuffixes     = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}*/
/*      case 6: {
			String[] goodSuffixes     = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}*/
/*      case 7: {
			String[] goodSuffixes     = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new TheLotteryBothDivs().find(goodSuffixes));
		}*/
		default:
			return -1;
		}
	}
}