package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] l = new int[n];
		int[] m = new int[n];
		int[] w = new int[n];
		IOUtils.readIntArrays(in, l, m, w);
		Map<IntPair, Solution> start = calculate(l, m, w, 0, n / 2);
		Map<IntPair, Solution> end = calculate(l, m, w, n / 2, n);
		long best = Long.MIN_VALUE;
		List<String> output = new ArrayList<>();
		for (Map.Entry<IntPair, Solution> entry : start.entrySet()) {
			IntPair otherKey = new IntPair(-entry.getKey().first, -entry.getKey().second);
			Solution other = end.get(otherKey);
			if (other == null) {
				continue;
			}
			if (entry.getValue().values[0] + other.values[0] > best) {
				best = entry.getValue().values[0] + other.values[0];
				output = new ArrayList<>(entry.getValue().output);
				output.addAll(other.output);
			}
		}
		if (best == Long.MIN_VALUE) {
			out.printLine("Impossible");
		}
		for (String s : output) {
			out.printLine(s);
		}
	}

	private Map<IntPair, Solution> calculate(int[] l, int[] m, int[] w, int from, int to) {
		int[] values = new int[3];
		Map<IntPair, Solution> result = new EHashMap<>();
		go(l, m, w, new ArrayList<String>(), from, to, result, values);
		return result;
	}

	private void go(int[] l, int[] m, int[] w, List<String> output, int from, int to, Map<IntPair, Solution> result, int[] values) {
		if (from == to) {
			Solution solution = new Solution(values, output);
			IntPair key = new IntPair(values[1] - values[0], values[2] - values[0]);
			if (result.containsKey(key) && result.get(key).values[0] >= values[0]) {
				return;
			}
			result.put(key, solution);
			return;
		}
		values[0] += l[from];
		values[1] += m[from];
		output.add("LM");
		go(l, m, w, output, from + 1, to, result, values);
		values[0] -= l[from];
		values[1] -= m[from];
		output.remove(output.size() - 1);
		values[0] += l[from];
		values[2] += w[from];
		output.add("LW");
		go(l, m, w, output, from + 1, to, result, values);
		values[0] -= l[from];
		values[2] -= w[from];
		output.remove(output.size() - 1);
		values[2] += w[from];
		values[1] += m[from];
		output.add("WM");
		go(l, m, w, output, from + 1, to, result, values);
		values[2] -= w[from];
		values[1] -= m[from];
		output.remove(output.size() - 1);
	}

	static class Solution {
		int[] values;
		List<String> output;

		public Solution(int[] values, List<String> output) {
			this.values = values.clone();
			this.output = new ArrayList<>(output);
		}
	}
}
