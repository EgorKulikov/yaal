import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;

import java.util.Arrays;

public class IPv444 {
	public long getMaximumMoney(String[] request, int[] price) {
		int[][] ip = new int[request.length][4];
		for (int i = 0; i < request.length; i++) {
			String[] splited = request[i].split("[.]");
			for (int j = 0; j < 4; j++) {
				if (splited[j].equals("*"))
					ip[i][j] = 1000;
				else
					ip[i][j] = Integer.parseInt(splited[j]);
			}
		}
		int[][] index = new int[4][1001];
		int[] asterikCount = new int[4];
		Arrays.fill(asterikCount, 1000);
		for (int i = 0; i < 4; i++) {
			Arrays.fill(index[i], request.length);
			for (int j = 0; j < request.length; j++) {
				if (ip[j][i] != 1000) {
					if (index[i][ip[j][i]] == request.length)
						asterikCount[i]--;
					index[i][ip[j][i]] = j;
				}
			}
		}
		int count = request.length + 1;
		long[][][][] ipCount = new long[count][count][count][count];
		for (int i = 0; i < count; i++) {
			if (i != count - 1 && index[0][ip[i][0]] != i)
				continue;
			for (int j = 0; j < count; j++) {
				if (j != count - 1 && index[1][ip[j][1]] != j)
					continue;
				for (int k = 0; k < count; k++) {
					if (k != count - 1 && index[2][ip[k][2]] != k)
						continue;
					for (int l = 0; l < count; l++) {
						if (l != count - 1 && index[3][ip[l][3]] != l)
							continue;
						ipCount[i][j][k][l] = 1;
						if (i == request.length)
							ipCount[i][j][k][l] *= asterikCount[0];
						if (j == request.length)
							ipCount[i][j][k][l] *= asterikCount[1];
						if (k == request.length)
							ipCount[i][j][k][l] *= asterikCount[2];
						if (l == request.length)
							ipCount[i][j][k][l] *= asterikCount[3];
					}
				}
			}
		}
		Integer[] order = SequenceUtils.order(Array.wrap(price), new ReverseComparator<Integer>());
		long answer = 0;
		for (int i : order) {
			long current = 0;
			int jFrom = ip[i][0] == 1000 ? 0 : index[0][ip[i][0]];
			int jTo = ip[i][0] == 1000 ? count - 1 : index[0][ip[i][0]];
			for (int j = jFrom; j <= jTo; j++) {
				int kFrom = ip[i][1] == 1000 ? 0 : index[1][ip[i][1]];
				int kTo = ip[i][1] == 1000 ? count - 1 : index[1][ip[i][1]];
				for (int k = kFrom; k <= kTo; k++) {
					int lFrom = ip[i][2] == 1000 ? 0 : index[2][ip[i][2]];
					int lTo = ip[i][2] == 1000 ? count - 1 : index[2][ip[i][2]];
					for (int l = lFrom; l <= lTo; l++) {
						int mFrom = ip[i][3] == 1000 ? 0 : index[3][ip[i][3]];
						int mTo = ip[i][3] == 1000 ? count - 1 : index[3][ip[i][3]];
						for (int m = mFrom; m <= mTo; m++) {
							current += ipCount[j][k][l][m];
							ipCount[j][k][l][m] = 0;
						}
					}
				}
			}
			answer += current * price[i];
			if (request[i].equals("*.*.*.*"))
				break;
		}
		return answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			IPv444Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				IPv444Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class IPv444Harness {
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
			String[] request          = {"66.37.210.86"};
			int[] price               = {47};
			long expected__           = 47L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}
		case 1: {
			String[] request          = {"0.0.0.*", "0.0.0.3", "0.0.0.5"};
			int[] price               = {1, 3, 9};
			long expected__           = 1010L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}
		case 2: {
			String[] request          = {"*.*.*.*", "123.456.789.0", "434.434.434.434", "999.*.999.*"};
			int[] price               = {1, 5, 3, 6};
			long expected__           = 1000005000006L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}
		case 3: {
			String[] request          = {"*.*.999.999", "888.888.999.*", "888.888.*.999", "777.777.777.777", "777.*.*.777"};
			int[] price               = {19, 33, 42, 777, 7};
			long expected__           = 26075718L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}
		case 4: {
			String[] request          = {"127.0.0.1", "*.0.0.*", "*.*.255.255", "192.68.*.*"};
			int[] price               = {999999, 629851, 294016, 438090};
			long expected__           = 1361957076132L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}

		// custom cases

      case 5: {
			String[] request          = {"1.*.*.*", "*.*.*.*"};
			int[] price               = {1, 1};
			long expected__           = 1000000000000L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}
/*      case 6: {
			String[] request          = ;
			int[] price               = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}*/
/*      case 7: {
			String[] request          = ;
			int[] price               = ;
			long expected__           = L;

			return verifyCase(casenum, expected__, new IPv444().getMaximumMoney(request, price));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


