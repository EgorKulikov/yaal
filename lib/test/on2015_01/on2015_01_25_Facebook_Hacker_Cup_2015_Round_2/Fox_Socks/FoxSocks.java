package on2015_01.on2015_01_25_Facebook_Hacker_Cup_2015_Round_2.Fox_Socks;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FoxSocks {
	private static final long MOD = (long) 1e9;
	long[] socks;
	long[] deltaStart;
	long[] deltaDelta;
	boolean[] clear;
	int[][][] oddity;
	int count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		count = in.readInt();
		int queryCount = in.readInt();
		long[] first = new long[6];
		long[] second = new long[6];
		long[] x = new long[6];
		long[] y = new long[6];
		long[] z = new long[6];
		IOUtils.readLongArrays(in, first, second, x, y, z);
		int[] s = parse(first, second, x, y, z, 0, count, MOD, 0);
		int[] o = parse(first, second, x, y, z, 1, queryCount, 4, 1);
		int[] a = parse(first, second, x, y, z, 2, queryCount, count, 1);
		int[] b = parse(first, second, x, y, z, 3, queryCount, count, 1);
		int[] c = parse(first, second, x, y, z, 4, queryCount, MOD, 0);
		int[] d = parse(first, second, x, y, z, 5, queryCount, MOD, 0);
		MiscUtils.decreaseByOne(a);
		int nodeCount = 4 * count;
		socks = new long[nodeCount];
		deltaStart = new long[nodeCount];
		deltaDelta = new long[nodeCount];
		clear = new boolean[nodeCount];
		oddity = new int[2][2][nodeCount];
		init(0, 0, count - 1, s);
		long answer = 0;
		for (int i = 0; i < queryCount; i++) {
			if (o[i] == 1) {
				add(a[i], b[i], c[i], d[i]);
				answer += (long) c[i] * b[i] + (long)b[i] * (b[i] - 1) / 2 % MOD * d[i];
			} else if (o[i] == 2) {
				answer += get(a[i], b[i]) + (long)c[i] * b[i];
				replace(a[i], b[i], c[i]);
			} else if (o[i] == 3) {
				answer += get(a[i], b[i]);
			} else {
				answer += getOddity(a[i], b[i]);
			}
			answer %= MOD;
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }

	private void replace(int from, int length, long value) {
		replace(0, 0, count - 1, from, Math.min(from + length - 1, count - 1), value);
		if (from + length - 1 >= count) {
			replace(0, 0, count - 1, 0, from + length - 1 - count, value);
		}
	}

	private void replace(int root, int from, int to, int left, int right, long value) {
		if (left > to || right < from) {
			return;
		}
		if (from >= left && to <= right) {
			clear(root, from, to);
			apply(root, from, to, from, value, 0);
			return;
		}
		pushDown(root, from, to);
		int middle = (from + to) >> 1;
		replace(2 * root + 1, from, middle, left, right, value);
		replace(2 * root + 2, middle + 1, to, left, right, value);
		update(root);
	}

	private long getOddity(int from, int length) {
		long result = getOddity(0, 0, count - 1, from, Math.min(from + length - 1, count - 1));
		if (from + length - 1 >= count) {
			result += getOddity(0, 0, count - 1, 0, from + length - 1 - count);
		}
		return result;
	}

	private long getOddity(int root, int from, int to, int left, int right) {
		if (left > to || right < from) {
			return 0;
		}
		if (from >= left && to <= right) {
			return oddity[0][1][root] + oddity[1][1][root];
		}
		pushDown(root, from, to);
		int middle = (from + to) >> 1;
		return getOddity(2 * root + 1, from, middle, left, right) + getOddity(2 * root + 2, middle + 1, to, left, right);
	}

	private long get(int from, int length) {
		long result = get(0, 0, count - 1, from, Math.min(from + length - 1, count - 1));
		if (from + length - 1 >= count) {
			result += get(0, 0, count - 1, 0, from + length - 1 - count);
		}
		return result % MOD;
	}

	private long get(int root, int from, int to, int left, int right) {
		if (left > to || right < from) {
			return 0;
		}
		if (from >= left && to <= right) {
			return socks[root];
		}
		pushDown(root, from, to);
		int middle = (from + to) >> 1;
		return (get(2 * root + 1, from, middle, left, right) + get(2 * root + 2, middle + 1, to, left, right)) % MOD;
	}

	private void add(int from, int length, long start, long delta) {
		add(0, 0, count - 1, from, Math.min(from + length - 1, count - 1), start, delta);
		if (from + length - 1 >= count) {
			add(0, 0, count - 1, 0, from + length - 1 - count, (start + delta * (count - from)) % MOD, delta);
		}
	}

	private void add(int root, int from, int to, int left, int right, long start, long delta) {
		if (left > to || right < from) {
			return;
		}
		if (from >= left && to <= right) {
			apply(root, from, to, left, start, delta);
			return;
		}
		pushDown(root, from, to);
		int middle = (from + to) >> 1;
		add(2 * root + 1, from, middle, left, right, start, delta);
		add(2 * root + 2, middle + 1, to, left, right, start, delta);
		update(root);
	}

	private void clear(int root, int from, int to) {
		clear[root] = true;
		socks[root] = 0;
		deltaStart[root] = 0;
		deltaDelta[root] = 0;
		oddity[0][0][root] = getEven(from, to);
		oddity[1][0][root] = to - from + 1 - oddity[0][0][root];
		oddity[0][1][root] = 0;
		oddity[1][1][root] = 0;
	}

	private void pushDown(int root, int from, int to) {
		int middle = (from + to) >> 1;
		if (clear[root]) {
			clear(2 * root + 1, from, middle);
			clear(2 * root + 2, middle + 1, to);
			clear[root] = false;
		}
		apply(2 * root + 1, from, middle, from, deltaStart[root], deltaDelta[root]);
		apply(2 * root + 2, middle + 1, to, from, deltaStart[root], deltaDelta[root]);
		deltaStart[root] = 0;
		deltaDelta[root] = 0;
	}

	private int getEven(int from, int to) {
		from += from & 1;
		to -= to & 1;
		return (to + 2 - from) >> 1;
	}

	private void apply(int root, int from, int to, int left, long start, long delta) {
		long curStart = (start + delta * (from - left)) % MOD;
		int length = to - from + 1;
		socks[root] += curStart * length;
		socks[root] += (long) length * (length - 1) / 2 % MOD * delta;
		socks[root] %= MOD;
		deltaStart[root] += curStart;
		deltaStart[root] %= MOD;
		deltaDelta[root] += delta;
		deltaDelta[root] %= MOD;
		int deltaEven = (int) ((start & 1) ^ (delta & left & 1));
		if (deltaEven != 0) {
			int temp = oddity[0][0][root];
			oddity[0][0][root] = oddity[0][1][root];
			oddity[0][1][root] = temp;
		}
		int deltaOdd = (int) ((start & 1) ^ (delta & (left ^ 1) & 1));
		if (deltaOdd != 0) {
			int temp = oddity[1][0][root];
			oddity[1][0][root] = oddity[1][1][root];
			oddity[1][1][root] = temp;
		}
	}

	private void init(int root, int from, int to, int[] s) {
		if (from == to) {
			socks[root] = s[from];
			oddity[from & 1][s[from] & 1][root]++;
		} else {
			int middle = (from + to) >> 1;
			init(2 * root + 1, from, middle, s);
			init(2 * root + 2, middle + 1, to, s);
			update(root);
		}
	}

	private void update(int root) {
		socks[root] = socks[2 * root + 1] + socks[2 * root + 2];
		if (socks[root] >= MOD) {
			socks[root] -= MOD;
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				oddity[i][j][root] = oddity[i][j][2 * root + 1] + oddity[i][j][2 * root + 2];
			}
		}
	}

	private int[] parse(long[] first, long[] second, long[] x, long[] y, long[] z, int at, int count, long mod, int delta) {
		int[] result = new int[count];
		result[0] = (int) first[at];
		result[1] = (int) second[at];
		for (int i = 2; i < count; i++) {
			result[i] = (int) ((x[at] * result[i - 2] + y[at] * result[i - 1] + z[at]) % mod + delta);
		}
		return result;
	}
}
