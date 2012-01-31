import java.util.Arrays;

public class NewItemShop {
	public double getMaximum(int swords, String[] customers) {
		int[][] time = new int[customers.length][];
		int[][] cost = new int[customers.length][];
		int[][] prob = new int[customers.length][];
		for (int i = 0; i < customers.length; i++) {
			String[] visits = customers[i].split(" ");
			time[i] = new int[visits.length];
			cost[i] = new int[visits.length];
			prob[i] = new int[visits.length];
			for (int j = 0; j < visits.length; j++) {
				String[] splitted = visits[j].split(",");
				time[i][j] = Integer.parseInt(splitted[0]);
				cost[i][j] = Integer.parseInt(splitted[1]);
				prob[i][j] = Integer.parseInt(splitted[2]);
			}
		}
		int[] index = new int[customers.length];
		int count = 0;
		for (int i = 0; i < customers.length; i++) {
			if (time[i].length == 1)
				index[i] = -1;
			else
				index[i] = count++;
		}
		int[] customer = new int[24];
		double[] probability = new double[24];
		int[] profit = new int[24];
		double[] remainingProbability = new double[customers.length];
		Arrays.fill(remainingProbability, 1);
		for (int i = 0; i < 24; i++) {
			customer[i] = -2;
			for (int j = 0; j < customers.length; j++) {
				for (int k = 0; k < time[j].length; k++) {
					if (time[j][k] == i) {
						customer[i] = index[j];
						probability[i] = prob[j][k] / 100. / remainingProbability[j];
						remainingProbability[j] -= prob[j][k] / 100.;
						profit[i] = cost[j][k];
					}
				}
			}
		}
		swords = Math.min(swords, customers.length);
		double[][][] result = new double[25][swords + 1][1 << count];
		boolean[][][] counted = new boolean[25][swords + 1][1 << count];
		return go(0, swords, (1 << count) - 1, result, counted, customer, probability, profit);
	}

	private double go(int hour, int swords, int mask, double[][][] result, boolean[][][] counted, int[] customer, double[] probability, int[] profit) {
		if (counted[hour][swords][mask])
			return result[hour][swords][mask];
		counted[hour][swords][mask] = true;
		if (hour == 24 || swords == 0)
			return 0;
		if (customer[hour] == -2 || customer[hour] >= 0 && (mask >> customer[hour] & 1) == 0)
			return result[hour][swords][mask] = go(hour + 1, swords, mask, result, counted, customer, probability, profit);
		int newMask = mask - (customer[hour] == -1 ? 0 : (1 << customer[hour]));
		return result[hour][swords][mask] = Math.max(profit[hour] + go(hour + 1, swords - 1, newMask, result, counted, customer, probability, profit),
			go(hour + 1, swords, newMask, result, counted, customer, probability, profit)) * probability[hour] +
			(1 - probability[hour]) * go(hour + 1, swords, mask, result, counted, customer, probability, profit);
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			NewItemShopHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				NewItemShopHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class NewItemShopHarness {
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
			int swords                = 1;
			String[] customers        = { "8,1,80 16,100,11", "12,10,100" };
			double expected__         = 19.0;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}
		case 1: {
			int swords                = 2;
			String[] customers        = { "8,1,80 16,100,11", "12,10,100" };
			double expected__         = 21.8;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}
		case 2: {
			int swords                = 1;
			String[] customers        = { "0,90,25 2,90,25 4,90,25 6,90,25", "7,100,80" };
			double expected__         = 90.0;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}
		case 3: {
			int swords                = 3;
			String[] customers        = { "17,31,41 20,59,26 23,53,5", "19,89,79", "16,32,38 22,46,26", "18,43,38 21,32,7" };
			double expected__         = 135.5121414;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}
		case 4: {
			int swords                = 5;
			String[] customers        = { "1,1,10", "2,2,9", "3,3,8", "4,4,7", "5,5,6", "6,6,5", "7,7,4", "8,8,3", "9,9,2", "10,10,1" };
			double expected__         = 2.1999744634845344;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}

		// custom cases

/*      case 5: {
			int swords                = ;
			String[] customers        = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}*/
/*      case 6: {
			int swords                = ;
			String[] customers        = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}*/
/*      case 7: {
			int swords                = ;
			String[] customers        = ;
			double expected__         = ;

			return verifyCase(casenum, expected__, new NewItemShop().getMaximum(swords, customers));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


