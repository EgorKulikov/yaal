import java.util.Arrays;

public class Zoo {
	public long theCount(int[] answers) {
		Arrays.sort(answers);
		long answer = 2;
		int index = 0;
		while (index + 1 < answers.length && answers[index] == index / 2 && answers[index + 1] == index / 2) {
			index += 2;
			answer <<= 1;
		}
		if (index == answers.length)
			answer >>= 1;
		while (index < answers.length && (index == 0 || answers[index] == answers[index - 1] + 1))
			index++;
		if (index != answers.length || answers[0] != 0)
			return 0;
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			ZooHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ZooHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ZooHarness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
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
			int[] answers             = {0, 1, 2, 3, 4};
			long expected__           = 2L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}
		case 1: {
			int[] answers             = {5, 8};
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}
		case 2: {
			int[] answers             = {0, 0, 0, 0, 0, 0};
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}
		case 3: {
			int[] answers             = {1, 0, 2, 0, 1};
			long expected__           = 8L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}
		case 4: {
			int[] answers             = {1, 0, 1};
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}

		// custom cases

/*      case 5: {
			int[] answers             = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}*/
/*      case 6: {
			int[] answers             = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}*/
/*      case 7: {
			int[] answers             = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new Zoo().theCount(answers));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


