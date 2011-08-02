import net.egork.string.StringUtils;

import java.util.Arrays;

public class YetAnotherBingoProblem {
	public int longestWinningSequence(String[] cards1, String[] cards2) {
		int[][] ourCards = convert(cards1);
		int[][] enemyCards = convert(cards2);
		boolean unique = false;
		for (int[] card : ourCards) {
			boolean found = false;
			for (int[] enemyCard : enemyCards) {
				if (Arrays.equals(card, enemyCard))
					found = true;
			}
			if (!found)
				unique = true;
		}
		if (!unique)
			return -1;
		int[] lies = new int[76];
		int[] count = new int[76];
		for (int[] card : ourCards) {
			for (int i : card)
				count[i]++;
		}
		for (int[] card : enemyCards) {
			for (int i : card)
				count[i]++;
		}
		for (int j = 0; j < ourCards.length; j++) {
			for (int k : ourCards[j])
				lies[k] += 1 << j;
		}
		for (int j = 0; j < enemyCards.length; j++) {
			for (int k : enemyCards[j])
				lies[k] += 1 << (ourCards.length + j);
		}
		int result = 0;
		int[] distance = new int[1 << (ourCards.length + enemyCards.length)];
		boolean[] used = new boolean[distance.length];
		for (int i = 1; i <= 75; i++) {
			for (int l = 0; l < ourCards.length; l++) {
			if ((lies[i] & (1 << l)) != 0) {
				Arrays.fill(distance, Integer.MAX_VALUE / 2);
				distance[(1 << ourCards.length + enemyCards.length) - 1 - (lies[i] & ((1 << ourCards.length) - 1)) + (1 << l)] = 0;
				Arrays.fill(used, false);
				for (int j = 0; j < distance.length; j++) {
					int index = -1;
					int min = Integer.MAX_VALUE / 2;
					for (int k = 0; k < distance.length; k++) {
						if (!used[k] && distance[k] < min) {
							min = distance[k];
							index = k;
						}
					}
					if (index == -1)
						break;
					used[index] = true;
					for (int k = 1; k <= 75; k++) {
						if (i != k) {
							int next = index - (index & lies[k]);
							distance[next] = Math.min(distance[next], distance[index] + 1);
						}
					}
				}
				result = Math.max(result, 75 - distance[1 << l]);
			}
			}
		}
		return result;
	}

	private int[][] convert(String[] cards) {
		String list = StringUtils.unite(cards);
		String[] eachCard = list.split(",");
		int[][] result = new int[eachCard.length][25];
		for (int i = 0; i < result.length; i++) {
			String[] current = eachCard[i].split(" ");
			for (int j = 0; j < 25; j++)
				result[i][j] = Integer.parseInt(current[j]);
			Arrays.sort(result[i]);
		}
		return result;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			YetAnotherBingoProblemHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				YetAnotherBingoProblemHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherBingoProblemHarness {
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
			String[] cards1           = {"60 28 ","8 25 54"," 31 13 26 71 20 66 55 72 67 18 51 52 48 63 53 ","42 16 ","1 30 56,", "27 43 70 48 54 24 73 37 41 51 58 55"," 71 12 60 49 64 47 35 4 ","16 69 14 40 50"} ;
			String[] cards2           = {"5 48 26 64 12 47 39 29 63 50 15 51 33 5","8 49 73 13 61 69 16 53 41 60 ","59 55,", "16 55 57 43 20 3 74 12 4","2 ","35 19 65 51 17 1 15 69 56"," 31 13 9 24 58"," ","38 75"} ;
			int expected__            = 74;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}
		case 1: {
			String[] cards1           = {"34 ","56"," 7 22 21 43 57 38 ","12 5 32"," 39 15 13"," 75 20 ","55 69 70 2","3 65 48 33 9 44"} ;
			String[] cards2           = {"46 18 3 33 34 58 24 36 30 50 ","45 11 56 25 57 60 54 74 42 8 70 40 20 2 1,26 61 20", " 43 52 ","2 22 33 53 44 60 27 57 12 7 15 29 73 50 ","2","8 56 34 75 67 37,47 61 36 75 45 22 17 49 33 59", " 48 39 69 27 3 70 12 46 54 1","1 67 ","43 73 5","2 68"} ;
			int expected__            = 73;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}
		case 2: {
			String[] cards1           = {"70 29 47 73 17 49 13 64 42 71 63 28 9 54 ","74 20 69 16 14 66 12 25 4","5 41 68"} ;
			String[] cards2           = {"4","7 16 68 74 71 42 49 66 45 25 13 9 12"," 17 ","20"," 5","4 70 28 64 69 73 63 14 41"," 29"} ;
			int expected__            = -1;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}
		case 3: {
			String[] cards1           = {"2 21 15 48 50 ","75 67 33 60 52 1","4 72 64 25 8 23 39 12 11 74 65 63 56 44 13,", "13 3","3 52 72 39 63 44 14 21 48 3","8 65 12 56"," 74 60 23 8 15 50 75", " 67 25 11 2,67 38 14 48 56 52 72 50 23 21 15 13 ","64 39 2 11 25 8 33 44 12 60", " 65 75 74,38 15 50 11 13 12 44 60 74 21 25"," 48 67 39 72 14 8 63 64 56 75 33 ","52 31 23"} ;
			String[] cards2           = {"7","4 21 12 60 25 38 15 11 49 ","54 8 63 33 65 56 52 13 14 72 75 23 48 50 67 3", "9,52 14 25 39 64 50 21 33 2 15 67 13 48 ","56 12 7","5 63 60 11 65 44 74 ", "23 72 29,25 12 74 2 64 31 39 33 ","14"," 21 13 72 15 23 8 60 5","6 50 38 75 ","44 46 67 1","1 48"} ;
			int expected__            = 73;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}

		// custom cases

/*      case 4: {
			String[] cards1           = ;
			String[] cards2           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}*/
/*      case 5: {
			String[] cards1           = ;
			String[] cards2           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}*/
/*      case 6: {
			String[] cards1           = ;
			String[] cards2           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new YetAnotherBingoProblem().longestWinningSequence(cards1, cards2));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


