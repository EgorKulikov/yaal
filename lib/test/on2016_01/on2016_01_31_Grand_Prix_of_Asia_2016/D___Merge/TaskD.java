package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.D___Merge;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntHashMap;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);
	int n;
	int[] p;
	int[] q;

	long[][] ordinary;
	IntHashMap[] special;

	int[][] f;
	int[][] s;
	int[][] by;
//	IntIntPair[][] pairs;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		p = readIntArray(in, n);
		q = readIntArray(in, n);
		ordinary = new long[n + 1][n + 1];
		fill(ordinary, -1);
		ordinary[n][n] = 1;
		f = new int[2 * n][4];
		s = new int[2 * n][4];
		by = new int[2 * n][4];
		special = new IntHashMap[2 * n + 1];
		for (int i = 0; i <= 2 * n; i++) {
			special[i] = new IntHashMap();
		}
//		pairs = new IntIntPair[n + 1][n + 1];
//		for (int i = 0; i <= n; i++) {
//			for (int j = 0; j <= n; j++) {
//				pairs[i][j] = new IntIntPair(i, j);
//			}
//		}
		out.printLine(calcOrdinary(0, 0));
	}

	private long calcOrdinary(int first, int second) {
		if (ordinary[first][second] != -1) {
			return ordinary[first][second];
		}
		long result;
		if (first != n && second != n && p[first] == q[second]) {
			result = calcSpecial(first, second + 1, first + 1, second);
		} else {
			result = 0;
			if (first != n) {
				result += calcOrdinary(first + 1, second);
			}
			if (second != n) {
				result += calcOrdinary(first, second + 1);
			}
		}
		if (result >= MOD) {
			result -= MOD;
		}
		return ordinary[first][second] = result;
	}

	private long calcSpecial(int first1, int second1, int first2, int second2) {
		int key = first1 * (n + 1) + first2;
		int sum = first1 + second1;
		if (special[sum].contains(key)) {
			return special[sum].get(key);
		}
		long result = 0;
		f[sum][0] = first1 + 1;
		s[sum][0] = second1;
		by[sum][0] = first1 != n ? p[first1] : -1;
		f[sum][1] = first1;
		s[sum][1] = second1 + 1;
		by[sum][1] = second1 != n ? q[second1] : -1;
		f[sum][2] = first2 + 1;
		s[sum][2] = second2;
		by[sum][2] = first2 != n ? p[first2] : -1;
		f[sum][3] = first2;
		s[sum][3] = second2 + 1;
		by[sum][3] = second2 != n ? q[second2] : -1;
		for (int i = 0; i < 4; i++) {
			if (by[sum][i] == -1) {
				continue;
			}
			int other = -1;
			if (by[sum][i] == by[sum][3 - i]) {
				other = 3 - i;
			}
			if (other == -1 || f[sum][i] == f[sum][other]) {
				result += calcOrdinary(f[sum][i], s[sum][i]);
			} else {
				result += calcSpecial(f[sum][i], s[sum][i], f[sum][other], s[sum][other]);
			}
			if (other != -1) {
				by[sum][other] = -1;
			}
			if (result >= MOD) {
				result -= MOD;
			}
		}
		special[sum].put(key, (int) result);
		return result;
	}

	static class State {
		int f1;
		int s1;
		int f2;
		int s2;

		public State(int f1, int s1, int f2, int s2) {
			this.f1 = f1;
			this.s1 = s1;
			this.f2 = f2;
			this.s2 = s2;
		}

		@Override
		public boolean equals(Object o) {
			State state = (State) o;

			if (f1 != state.f1) {
				return false;
			}
			if (s1 != state.s1) {
				return false;
			}
			if (f2 != state.f2) {
				return false;
			}
			return s2 == state.s2;
		}

		@Override
		public int hashCode() {
			int result = f1;
			result = 31 * result + s1;
			result = 31 * result + f2;
			result = 31 * result + s2;
			return result;
		}
	}
}
