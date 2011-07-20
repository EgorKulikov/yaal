import net.egork.numbers.IntegerUtils;

import java.util.HashMap;
import java.util.Map;

public class ArtShift {
	public int maxShifts(String sequence) {
		int countW = 0;
		int countB = 0;
		for (char c : sequence.toCharArray()) {
			if (c == 'W')
				countW++;
			else
				countB++;
		}
		int different = Math.min(countW, countB);
		Map<Long, Integer> result = new HashMap<Long, Integer>();
		return go(sequence.length(), different, 1, result);
	}

	private int go(int length, int different, int lcm, Map<Long, Integer> result) {
		long key = length + 100 * different + 10000L * lcm;
		if (result.containsKey(key))
			return result.get(key);
		if (length == 0 || different == 0) {
			result.put(key, lcm);
			return lcm;
		}
		int best = lcm;
		for (int i = 1; i <= length; i++)
			best = Math.max(best, go(length - i, different - 1, (int) IntegerUtils.lcm(lcm, i), result));
		result.put(key, best);
		return best;
	}


	// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			ArtShiftHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ArtShiftHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ArtShiftHarness {
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
			String sequence           = "BW";
			int expected__            = 2;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}
		case 1: {
			String sequence           = "BBBWBBB";
			int expected__            = 7;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}
		case 2: {
			String sequence           = "BWBWB";
			int expected__            = 6;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}
		case 3: {
			String sequence           = "WWWWWWWWW";
			int expected__            = 1;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}
		case 4: {
			String sequence           = "WWWWWWWWWBBWB";
			int expected__            = 60;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}

		// custom cases

/*      case 5: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}*/
/*      case 6: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}*/
/*      case 7: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new ArtShift().maxShifts(sequence));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


