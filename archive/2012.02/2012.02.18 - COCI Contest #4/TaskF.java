package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<String> tokens = new ArrayList<String>();
		while (true) {
			String token = in.readString();
			if ("$".equals(token))
				break;
			tokens.add(token);
		}
		List<String> sample = new ArrayList<String>();
		while (true) {
			String token = in.readString();
			if ("$".equals(token))
				break;
			sample.add(token);
		}
		long[] hash = new long[tokens.size() + 1];
		long[] value = new long[tokens.size()];
		int[] last = new int[tokens.size()];
		Arrays.fill(last, Integer.MIN_VALUE);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < tokens.size(); i++) {
			if (map.containsKey(tokens.get(i)))
				last[i] = map.get(tokens.get(i));
			map.put(tokens.get(i), i);
		}
		long base = 1000000007;
		long[] power = new long[sample.size() + 1];
		power[0] = 1;
		for (int i = 1; i < power.length; i++)
			power[i] = power[i - 1] * base;
		for (int i = 0; i < tokens.size(); i++) {
			hash[i + 1] = hash[i] * base;
			if (i >= sample.size())
				hash[i + 1] -= value[i - sample.size()] * power[sample.size()];
			if (last[i] > i - sample.size()) {
				value[last[i]] = i - last[i];
				hash[i + 1] += value[last[i]] * power[i - last[i]];
			}
		}
		long sampleHash = 0;
		map.clear();
		for (int i = 0; i < sample.size(); i++) {
			sampleHash *= base;
			if (map.containsKey(sample.get(i))) {
				int delta = i - map.get(sample.get(i));
				sampleHash += delta * power[delta];
			}
			map.put(sample.get(i), i);
		}
		for (int i = sample.size(); i <= tokens.size(); i++) {
			if (sampleHash == hash[i]) {
				out.printLine(i - sample.size() + 1);
				return;
			}
		}
		out.printLine("Oops");
	}
}
