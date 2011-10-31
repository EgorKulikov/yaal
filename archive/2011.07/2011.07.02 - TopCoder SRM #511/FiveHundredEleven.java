import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;

public class FiveHundredEleven {
	public String theWinner(int[] cards) {
		String first = "Fox Ciel";
		String second = "Toastman";
		int[][] winner = new int[512][cards.length + 1];
		int[][] newUselessCount = new int[512][cards.length];
		for (int mask = 0; mask < 512; mask++) {
			for (int i = 0, cardsLength = cards.length; i < cardsLength; i++) {
				int card = cards[i];
				if ((mask & card) != 0) {
					int newMask = mask - (mask & card);
					int newUseless = 0;
					for (int i1 = 0, cardsLength1 = cards.length; i1 < cardsLength1; i1++) {
						int otherCard = cards[i1];
						if (i != i1 && (mask & otherCard) != 0 && (newMask & otherCard) == 0)
							newUseless++;
					}
					newUselessCount[mask][i] = newUseless;
				}
			}
		}
		int result = go(511, CollectionUtils.count(Array.wrap(cards), 0), winner, cards, newUselessCount);
		if (result == 1)
			return first;
		else
			return second;
	}

	private int go(int mask, int useless, int[][] winner, int[] cards, int[][] newUselessCount) {
		if (winner[mask][useless] != 0)
			return winner[mask][useless];
		if (mask == 0)
			return winner[mask][useless] = 1;
		winner[mask][useless] = 1;
		if (useless != 0 && go(mask, useless - 1, winner, cards, newUselessCount) == -1)
			return 1;
		for (int i = 0, cardsLength = cards.length; i < cardsLength; i++) {
			int card = cards[i];
			if ((mask & card) != 0) {
				int newMask = mask - (mask & card);
				int newUseless = useless + newUselessCount[mask][i];
				if (go(newMask, newUseless, winner, cards, newUselessCount) == -1)
					return 1;
			}
		}
		return winner[mask][useless] = -1;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			FiveHundredElevenHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FiveHundredElevenHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FiveHundredElevenHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			int[] cards               = {3, 5, 7, 9, 510};
			String expected__         = "Fox Ciel";

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}
		case 1: {
			int[] cards               = {0, 0, 0, 0};
			String expected__         = "Toastman";

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}
		case 2: {
			int[] cards               = {511};
			String expected__         = "Toastman";

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}
		case 3: {
			int[] cards               = {5, 58, 192, 256};
			String expected__         = "Fox Ciel";

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}

		// custom cases

/*      case 4: {
			int[] cards               = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}*/
/*      case 5: {
			int[] cards               = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}*/
/*      case 6: {
			int[] cards               = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new FiveHundredEleven().theWinner(cards));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


