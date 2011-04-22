package April2011.TopCoderSRM503;

import java.util.Arrays;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class KingdomXCitiesandVillages {
	public double determineLength(int[] cityX, int[] cityY, int[] villageX, int[] villageY) {
		int villageCount = villageX.length;
		int cityCount = cityX.length;
		double result = 0;
		for (int i = 0; i < villageCount; i++) {
			double minCityDistance = Double.POSITIVE_INFINITY;
			for (int j = 0; j < cityCount; j++)
				minCityDistance = Math.min(minCityDistance, Math.hypot(cityX[j] - villageX[i], cityY[j] - villageY[i]));
			double probability = 1;
			double[] villageDistances = new double[villageCount];
			for (int j = 0; j < villageCount; j++) {
				if (j == i)
					villageDistances[j] = Double.POSITIVE_INFINITY;
				else
					villageDistances[j] = Math.hypot(villageX[j] - villageX[i], villageY[j] - villageY[i]);
			}
			Arrays.sort(villageDistances);
			for (int j = 0; j < villageCount; j++) {
				if (villageDistances[j] > minCityDistance) {
					result += probability * minCityDistance;
					break;
				}
				double currentProbability = probability / (j + 2);
				result += currentProbability * villageDistances[j];
				probability -= currentProbability;
			}
		}
		return result;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			KingdomXCitiesandVillagesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				KingdomXCitiesandVillagesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class KingdomXCitiesandVillagesHarness {
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
			int[] cityX               = {3};
			int[] cityY               = {0};
			int[] villageX            = {3,3};
			int[] villageY            = {2,1};
			double expected__         = 2.5;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}
		case 1: {
			int[] cityX               = {1,4,7,10};
			int[] cityY               = {5,5,5,5};
			int[] villageX            = {1,4,7,10};
			int[] villageY            = {4,4,4,4};
			double expected__         = 4.0;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}
		case 2: {
			int[] cityX               = {1,2,3};
			int[] cityY               = {4,4,4};
			int[] villageX            = {4,5,6};
			int[] villageY            = {4,4,4};
			double expected__         = 4.166666666666667;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}

		// custom cases

/*      case 3: {
			int[] cityX               = ;
			int[] cityY               = ;
			int[] villageX            = ;
			int[] villageY            = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}*/
/*      case 4: {
			int[] cityX               = ;
			int[] cityY               = ;
			int[] villageX            = ;
			int[] villageY            = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}*/
/*      case 5: {
			int[] cityX               = ;
			int[] cityY               = ;
			int[] villageX            = ;
			int[] villageY            = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new KingdomXCitiesandVillages().determineLength(cityX, cityY, villageX, villageY));
		}*/
		default:
			return -1;
		}
	}
}