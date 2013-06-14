package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<Integer> answers = new ArrayList<Integer>();
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		int value = 1;
		int periodStart;
		for (int i = 0; ; i++) {
			if (index.containsKey(value)) {
				periodStart = index.get(value);
				break;
			}
			index.put(value, i);
			answers.add(value);
			value *= 2;
			char[] s = Integer.toString(value).toCharArray();
			Collections.sort(Array.wrap(s));
			value = Integer.parseInt(new String(s));
		}
		int number = in.readInt() - 1;
		if (number >= answers.size()) {
			number -= periodStart;
			number %= answers.size() - periodStart;
			number += periodStart;
		}
		out.printLine(answers.get(number));
	}
}
