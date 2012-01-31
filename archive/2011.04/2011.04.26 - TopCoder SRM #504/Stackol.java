import net.egork.string.StringUtils;

public class Stackol {
	public int countPrograms(String[] outputFragments, int k) {
		char[] output = StringUtils.unite(outputFragments).toCharArray();
		int length = output.length;
		SumIntervalTree[] trees = new SumIntervalTree[k + 1];
		for (int i = 0; i <= k; i++)
			trees[i] = new SumIntervalTree(length + 1);
		int[][] countA = new int[length + 1][length + 1];
		for (int i = 0; i < length; i++) {
			int count = 0;
			for (int j = i; j < length; j++) {
				if (output[j] == 'A')
					count++;
				countA[i][j + 1] = count;
			}
		}
		trees[0].putSegment(0, 1, 1);
		for (int i = 0; i < length; i++) {
			int countB = 0;
			for (int j = 0; j < k; j++) {
				trees[j + 1].putSegment(i, i + 1, trees[j].get(i));
			}
			for (int j = i; j < length; j++) {
				if (output[j] == 'B')
					countB++;
				int minA, maxA;
				if (j != length - 1 && output[j + 1] == 'B') {
					if (output[i] == 'A') {
						if (countB == 0) {
							int start = j + 1;
							int end = j + 2;
							for (int l = 0; l < k; l++)
								trees[l + 1].putSegment(start, end, trees[l].get(i));
						}
						continue;
					} else {
						minA = countB - 1;
						maxA = countB - 1;
					}
				} else {
					if (output[i] == 'A') {
						minA = countB;
						maxA = countB;
					} else {
						minA = countB - 1;
						maxA = countB;
					}
				}
				int start = findFirst(countA[j + 1], j + 1, minA - 1);
				int end = findFirst(countA[j + 1], j + 1, maxA);
				for (int l = 0; l < k; l++)
					trees[l + 1].putSegment(start, end, trees[l].get(i));
			}
		}
		for (int j = 0; j < k; j++)
			trees[j + 1].putSegment(length, length + 1, trees[j].get(length));
		long result = 0;
		for (int i = 0; i <= k; i++)
			result += trees[i].get(length);
		return (int) (result % SumIntervalTree.MOD);
	}

	private int findFirst(int[] array, int from, int value) {
		int left = from;
		int right = array.length;
		while (left < right) {
			int index = (left + right) / 2;
			if (array[index] <= value)
				left = index + 1;
			else
				right = index;
		}
		return left;
	}

	private static class SumIntervalTree {
		private int[] left, right, value;
		private static final int MOD = 1000000007;

		public SumIntervalTree(int size) {
			left = new int[4 * size];
			right = new int[4 * size];
			value = new int[4 * size];
			initTree(0, size, 0);
		}
		
		private void initTree(int left, int right, int root) {
			this.left[root] = left;
			this.right[root] = right;
			if (right - left > 1) {
				initTree(left, (left + right + 1) / 2, 2 * root + 1);
				initTree((left + right + 1) / 2, right, 2 * root + 2);
			}
		}


		public void putSegment(int left, int right, int value) {
			putSegment(left, right, value, 0);
		}

		private void putSegment(int left, int right, int value, int root) {
			if (this.left[root] >= right || this.right[root] <= left)
				return;
			if (this.left[root] >= left && this.right[root] <= right) {
				this.value[root] += value;
				if (this.value[root] >= MOD)
					this.value[root] -= MOD;
				return;
			}
			putSegment(left, right, value, 2 * root + 1);
			putSegment(left, right, value, 2 * root + 2);
		}

		public int get(int position) {
			return get(position, 0);
		}

		private int get(int position, int root) {
			if (right[root] <= position || left[root] > position)
				return 0;
			int result = value[root];
			if (right[root] - left[root] == 1)
				return result;
			result += get(position, 2 * root + 1);
			if (result >= MOD)
				result -= MOD;
			result += get(position, 2 * root + 2);
			if (result >= MOD)
				result -= MOD;
			return result;
		}
	}

	// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			StackolHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				StackolHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class StackolHarness {
	public static void run_test( int casenum ) {
		if ( casenum != -1 ) {
			if ( runTestCase( casenum ) == -1 )
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for ( int i=0;; ++i ) {
			int x = runTestCase(i);
			if ( x == -1 ) {
				if ( i >= 100 ) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if ( total == 0 ) {
			System.err.println("No test cases run.");
		} else if ( correct < total ) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase( int casenum, int expected, int received ) { 
		System.err.print("Example " + casenum + "... ");
		if ( compareOutput( expected, received ) ) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase( int casenum ) {
		switch( casenum ) {
		case 0: {
			String[] outputFragments  = {"A"};
			int k                     = 2;
			int expected__            = 3;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}
		case 1: {
			String[] outputFragments  = {"AAAA","BABA"};
			int k                     = 1;
			int expected__            = 0;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}
		case 2: {
			String[] outputFragments  = {"AB"};
			int k                     = 2;
			int expected__            = 1;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}
		case 3: {
			String[] outputFragments  = {"AAABABABAABA", "AA", "BBAB"};
			int k                     = 3;
			int expected__            = 94;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}
		case 4: {
			String[] outputFragments  = {"AAAAAAAAAAAA","B"};
			int k                     = 4;
			int expected__            = 120;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}

		// custom cases

/*      case 5: {
			String[] outputFragments  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}*/
/*      case 6: {
			String[] outputFragments  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}*/
/*      case 7: {
			String[] outputFragments  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase( casenum, expected__, new Stackol().countPrograms( outputFragments, k ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


