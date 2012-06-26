package net.egork;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] original = in.readString().toCharArray();
		char[] sample = in.readString().toCharArray();
		List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < original.length; i++) {
			if (original[i] == sample[i])
				continue;
			int right = -1;
			for (int j = i + 1; j < sample.length; j++) {
				if (sample[i] == original[j]) {
					right = j;
					break;
				}
			}
			for (int j = i, k = right; j < k; j++, k--) {
				char temp = original[j];
				original[j] = original[k];
				original[k] = temp;
			}
			answer.add(Pair.makePair(i + 1, right + 1));
		}
		out.printLine(answer.size());
		for (Pair<Integer, Integer> pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}
}
