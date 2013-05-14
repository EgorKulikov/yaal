package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] state = in.readString().toCharArray();
		boolean single = true;
		for (int i = 1; i < count; i++) {
			if (state[i] == '*' && state[i - 1] == '*') {
				single = false;
			}
		}
		if (single) {
			int total = CollectionUtils.count(Array.wrap(state), '*');
			out.printLine(total);
			boolean first = true;
			for (int i = 0; i < count; i++) {
				if (state[i] == '*') {
					if (first) {
						first = false;
						out.printLine(i + 1);
					} else
						out.printLine("Ctrl+" + (i + 1));
				}
			}
			return;
		}
		int[] right = new int[count + 1];
		right[count] = 0;
		for (int i = count - 1; i >= 0; i--) {
			if (state[i] == '.') {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = right[i + 1] - 1;
			}
		}
		int[] left = new int[count];
		left[0] = 0;
		for (int i = 0; i < count - 1; i++) {
			if (state[i] == '.')
				left[i + 1] = left[i] + 1;
			else
				left[i + 1] = left[i] - 1;
		}
		int max = Integer.MIN_VALUE;
		int maxRight = right[count];
		int from = -1;
		int to = -1;
		int position = count;
		for (int i = count - 1; i >= 0; i--) {
			if (right[i] > maxRight) {
				position = i;
				maxRight = right[i];
			}
			if (left[i] + maxRight > max) {
				max = left[i] + maxRight;
				from = i;
				to = position;
			}
		}
		List<String> answer = new ArrayList<String>();
		answer.add(Integer.toString(from + 1));
		answer.add("Shift+" + to);
		for (int i = 0; i < count; i++) {
			if ((i >= from && i < to) ^ (state[i] == '*'))
				answer.add("Ctrl+" + (i + 1));
		}
		out.printLine(answer.size());
		for (String s : answer)
			out.printLine(s);
	}
}
