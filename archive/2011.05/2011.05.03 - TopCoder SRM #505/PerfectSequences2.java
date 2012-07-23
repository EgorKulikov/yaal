import java.util.Arrays;

public class PerfectSequences2 {
	public long minimumMoves(int[] seq) {
		Arrays.sort(seq);
		long answer = 0;
		for (int aSeq : seq)
			answer += Math.abs(aSeq);
		for (int x = 0; 2 * x + 1 < seq.length; x++) {
			if (x % 2 == 0) {
				long candidate = 0;
				for (int i = 0; i < x; i++)
					candidate += Math.abs(seq[i] + 1);
				for (int i = x; i < seq.length - 2; i++)
					candidate += Math.abs(seq[i] - 1);
				candidate += Math.abs(seq[seq.length - 2] - 2);
				candidate += Math.abs(seq[seq.length - 1] - seq.length + 2 * x);
				answer = Math.min(answer,  candidate);
			}
			if ((seq.length % 2 + x % 2) == 1) {
				long candidate = 0;
				for (int i = seq.length - x; i < seq.length; i++)
					candidate += Math.abs(seq[i] - 1);
				for (int i = 2; i < seq.length - x; i++)
					candidate += Math.abs(seq[i] + 1);
				candidate += Math.abs(seq[1] + 2);
				candidate += Math.abs(seq[0] + seq.length - 2 * x);
				answer = Math.min(answer,  candidate);
			}
		}
		if (seq.length % 2 == 1) {
			for (int y = 0; y * 4 + 5 <= seq.length; y++) {
				int x = (seq.length - y * 4 - 3) / 2;
				long candidate = Math.abs(seq[0] + x);
				for (int i = 1; i < 2 * y + 2; i++)
					candidate += Math.abs(seq[i] + 1);
				for (int i = 2 * y + 2; i < seq.length; i++)
					candidate += Math.abs(seq[i] - 1);
				answer = Math.min(answer,  candidate);
				candidate = Math.abs(seq[seq.length - 1] - x);
				for (int i = 0; i < 2 * x + 2 * y + 1; i++)
					candidate += Math.abs(seq[i] + 1);
				for (int i = 2 * x + 2 * y + 1; i < seq.length - 2; i++)
					candidate += Math.abs(seq[i] - 1);
				answer = Math.min(answer,  candidate);
			}
		}
		if ((seq.length & 3) == 1) {
			long candidate = 0;
			for (int i = 0; i < seq.length / 2; i++)
				candidate += Math.abs(seq[i] + 1);
			for (int i = seq.length / 2; i < seq.length - 1; i++)
				candidate += Math.abs(seq[i] - 1);
			answer = Math.min(answer,  candidate);
		}
		if ((seq.length & 3) == 1) {
			long candidate = 0;
			for (int i = 1; i < seq.length / 2 + 1; i++)
				candidate += Math.abs(seq[i] + 1);
			for (int i = seq.length / 2 + 1; i < seq.length; i++)
				candidate += Math.abs(seq[i] - 1);
			answer = Math.min(answer,  candidate);
		}
		if (seq.length >= 2) {
			for (int i = 0; i < seq.length; i++) {
				for (int j = 0; j < seq.length; j++) {
					if (i == j)
						continue;
					long candidate = Math.abs(seq[i]);
					long sum = 0;
					for (int k = 0; k < seq.length; k++) {
						if (k != i && k != j)
							sum -= seq[k];
					}
					candidate += Math.abs(seq[j] - sum);
					answer = Math.min(answer, candidate);
				}
			}
		}
		return answer;
	}

// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			PerfectSequences2Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PerfectSequences2Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PerfectSequences2Harness {
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
			int[] seq                 = {-1000000000};
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 1: {
			int[] seq                 = {-1,1};
			long expected__           = 2L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 2: {
			int[] seq                 = {4,3};
			long expected__           = 3L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 3: {
			int[] seq                 = {17,95,-79};
			long expected__           = 33L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 4: {
			int[] seq                 = {10,9,8};
			long expected__           = 21L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 5: {
			int[] seq                 = {-2,-29,-13};
			long expected__           = 38L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
		case 6: {
			int[] seq                 = {-7,-31,2,-14};
			long expected__           = 48L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}

		// custom cases

      case 7: {
			int[] seq                 = {3, 2, 1, 2, 1};
			long expected__           = 0L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}
/*      case 8: {
			int[] seq                 = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}*/
/*      case 9: {
			int[] seq                 = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new PerfectSequences2().minimumMoves(seq));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


