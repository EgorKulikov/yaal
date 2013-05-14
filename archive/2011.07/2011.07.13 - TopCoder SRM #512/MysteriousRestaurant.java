public class MysteriousRestaurant {
	public int maxDays(String[] prices, int budget) {
		int[][] cost = new int[prices.length][prices[0].length()];
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].length; j++)
				cost[i][j] = decode(prices[i].charAt(j));
		}
		for (int i = 0; i < cost.length; i++) {
			int totalCost = 0;
			for (int j = 0; j < 7; j++) {
				int weekdayCost = Integer.MAX_VALUE;
				for (int k = 0; k < cost[0].length; k++) {
					int dishCost = 0;
					for (int l = j; l <= i; l += 7)
						dishCost += cost[l][k];
					weekdayCost = Math.min(weekdayCost, dishCost);
				}
				totalCost += weekdayCost;
			}
			if (totalCost > budget)
				return i;
		}
		return cost.length;
	}

	private int decode(char c) {
		if (Character.isDigit(c))
			return c - '0';
		if (Character.isUpperCase(c))
			return c - 'A' + 10;
		return c - 'a' + 36;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			MysteriousRestaurantHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				MysteriousRestaurantHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MysteriousRestaurantHarness {
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
			String[] prices           = {"26", "14", "72", "39", "32", "85", "06"};
			int budget                = 13;
			int expected__            = 5;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}
		case 1: {
			String[] prices           = {"26", "14", "72", "39", "32", "85", "06", "91"};
			int budget                = 20;
			int expected__            = 8;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}
		case 2: {
			String[] prices           = {"SRM", "512"};
			int budget                = 4;
			int expected__            = 0;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}
		case 3: {
			String[] prices           = {"Dear", "Code", "rsHa", "veFu", "nInT", "heCh", "alle", "ngeP", "hase", "andb", "ecar", "eful"};
			int budget                = 256;
			int expected__            = 10;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}

		// custom cases

/*      case 4: {
			String[] prices           = ;
			int budget                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}*/
/*      case 5: {
			String[] prices           = ;
			int budget                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}*/
/*      case 6: {
			String[] prices           = ;
			int budget                = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new MysteriousRestaurant().maxDays(prices, budget));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


