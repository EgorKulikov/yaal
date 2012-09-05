package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Squarks {
	int[] res;
	int[] sum;
	int[] cnt;
	int smallestNonZeroSum;
	List<String> solutions;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Map<Integer, Integer> sumMap = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n * (n - 1) / 2; ++i) {
			int a = in.readInt();
			if (!sumMap.containsKey(a)) sumMap.put(a, 0);
			sumMap.put(a, sumMap.get(a) + 1);
		}
		sum = new int[sumMap.size()];
		cnt = new int[sumMap.size()];
		int ptr = 0;
		for (Map.Entry<Integer, Integer> e : sumMap.entrySet()) {
			sum[ptr] = e.getKey();
			cnt[ptr] = e.getValue();
			++ptr;
		}
		if (ptr != sum.length) throw new RuntimeException();
		solutions = new ArrayList<String>();
		res = new int[n];
		int[] toDecrease = new int[n * n];
		outer: for (int ii = 2; ii < ptr; ++ii) {
			int smallest = (sum[0] + sum[1] - sum[ii]) / 2;
			res[0] = smallest;
			smallestNonZeroSum = 0;
			int ntd = 0;
			for (int i = 1; i < n; ++i) {
				res[i] = sum[smallestNonZeroSum] - res[0];
				if (res[i] <= res[i - 1]) {
					for (int k = 0; k < ntd; ++k) {
						++cnt[toDecrease[k]];
					}
					continue outer;
				}
				for (int j = 0; j < i; ++j) {
					int s = res[i] + res[j];
					int left = smallestNonZeroSum - 1;
					int right = sum.length;
					int middle = -1;
					boolean ok = false;
					while (right - left > 1) {
						middle = (left + right) / 2;
						if (sum[middle] == s) {
							ok = true;
							break;
						} else if (sum[middle] < s)
							left = middle;
						else
							right = middle;
					}
					if (ok && cnt[middle] == 0) ok = false;
					if (!ok) {
						for (int k = 0; k < ntd; ++k) {
							++cnt[toDecrease[k]];
						}
						continue outer;
					}
					--cnt[middle];
					toDecrease[ntd++] = middle;
					while (smallestNonZeroSum < cnt.length && cnt[smallestNonZeroSum] == 0) ++smallestNonZeroSum;
				}
			}
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < n; ++i) {
				if (i > 0) b.append(" ");
				b.append(res[i]);
			}
			solutions.add(b.toString());
			for (int k = 0; k < ntd; ++k) {
				++cnt[toDecrease[k]];
			}
		}
		out.printLine(solutions.size());
		for (String s : solutions) out.printLine(s);
	}

	private void rec(int at) {
	}
}
