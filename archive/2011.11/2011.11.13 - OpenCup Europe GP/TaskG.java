package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
	static final long HASH_BASE = 3137;
	static final long HASH_BASE_INV = pow(HASH_BASE, Long.MAX_VALUE);

	private static long pow(long a, long k) {
		if (k == 0)
			return 1;
		else if (k % 2 == 0)
			return pow(a * a, k / 2);
		else
			return a * pow(a, k - 1);
	}

	int[] next;
	int[] prevFirst;
	int[] prevNext;
	boolean[] val;
	boolean[] mark;
	boolean[] cycle;
	int[] cycSeq;
	int cycLen;
	int res;
	long needHash;
	int[] stack;
	int stackSize;
	int zeroStackIndex;
	long hashPw;
	int n;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int c = in.readInt();
		int k = in.readInt();
		int m = in.readInt();
		n = in.readInt();
		String s = in.readString();
		needHash = 0;
		for (int i = 0; i < s.length(); ++i) {
			needHash = needHash * HASH_BASE + (s.charAt(i) - '0');
		}
		next = new int[m];
		val = new boolean[m];
		for (int i = 0; i < m; ++i) {
			long tmp = i;
			tmp *= a;
			tmp += c;
			tmp /= k;
			tmp %= m;
			next[i] = (int) tmp;
			val[i] = next[i] >= m / 2;
		}
		prevFirst = new int[m];
		Arrays.fill(prevFirst, -1);
		prevNext = new int[m];
		for (int i = 0; i < m; ++i) {
			prevNext[i] = prevFirst[next[i]];
			prevFirst[next[i]] = i;
		}
		mark = new boolean[m];
		cycle = new boolean[m];
		cycSeq = new int[m];
		stack = new int[m];
		res = 0;
		hashPw = 1;
		for (int i = 0; i < n - 1; ++i)
			hashPw *= HASH_BASE;
		for (int strangeStart = 0; strangeStart < m; ++strangeStart) {
			if (!mark[strangeStart]) {
				int pos = strangeStart;
				while (!mark[pos]) {
					mark[pos] = true;
					pos = next[pos];
				}
				cycLen = 0;
				while (!cycle[pos]) {
					cycle[pos] = true;
					cycSeq[cycLen++] = pos;
					pos = next[pos];
				}
				long cycleHash = 0;
				long cyclePow = 1;
				for (int i = 0; i < cycLen; ++i) {
					cycleHash *= HASH_BASE;
					cyclePow *= HASH_BASE;
					if (val[cycSeq[i]]) ++cycleHash;
				}
				long posHash = repeatCycle(cycleHash, cyclePow, n / cycLen);
				int tmp = pos;
				int rem = n % cycLen;
				for (int i = 0; i < rem; ++i) {
					posHash *= HASH_BASE;
					if (val[tmp]) ++posHash;
					tmp = next[tmp];
				}
				goOut(posHash, 0);
				for (int i = cycLen - 1; i >= 1; --i) {
					int last = (i + n) % cycLen;
					if (val[cycSeq[last]])
						--posHash;
					posHash *= HASH_BASE_INV;
					if (val[cycSeq[i]])
						posHash += hashPw;
					goOut(posHash, i);
				}
			}
		}
		out.printLine(res);
	}

	private void goOut(long posHash, int cycIndex) {
		stackSize = 0;
		zeroStackIndex = cycIndex;
		rec(posHash, cycSeq[cycIndex]);
	}

	private void rec(long posHash, int pos) {
		if (posHash == needHash)
			++res;
		mark[pos] = true;
		int cur = prevFirst[pos];
		while (cur >= 0) {
			if (!cycle[cur]) {
				long newHash = posHash;
				if (stackSize >= n) {
					if (val[stack[stackSize - n]])
						--newHash;
				} else {
					if (val[cycSeq[(zeroStackIndex + (n - stackSize - 1)) % cycLen]])
						--newHash;
				}
				newHash *= HASH_BASE_INV;
				if (val[cur])
					newHash += hashPw;
				stack[stackSize++] = cur;
				rec(newHash, cur);
				--stackSize;
			}
			cur = prevNext[cur];
		}
	}

	private long repeatCycle(long cycleHash, long cyclePow, int times) {
		if (times == 0)
			return 0;
		else if (times % 2 == 0) {
			return repeatCycle(cycleHash * cyclePow + cycleHash, cyclePow * cyclePow, times / 2);
		} else {
			return repeatCycle(cycleHash, cyclePow, times - 1) * cyclePow + cycleHash;
		}
	}
}
