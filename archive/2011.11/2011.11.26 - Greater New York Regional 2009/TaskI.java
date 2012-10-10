package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TaskI {
	private Map<String, Character> last = new HashMap<String, Character>();

	{
		Queue<String> queue = new ArrayDeque<String>();
		last.put("ABCDEF ", ' ');
		queue.add("ABCDEF ");
		while (!queue.isEmpty()) {
			char[] current = queue.poll().toCharArray();
			for (int i = 1; i < 6; i++)
				trySwap(current, i - 1, i, queue);
			trySwap(current, 0, 5, queue);
			trySwap(current, 1, 6, queue);
			trySwap(current, 4, 6, queue);
		}
	}

	private void trySwap(char[] current, int first, int second, Queue<String> queue) {
		if (current[first] != ' ' && current[second] != ' ')
			return;
		char temp = current[first];
		current[first] = current[second];
		current[second] = temp;
		String candidate = new String(current);
		if (!last.containsKey(candidate)) {
			last.put(candidate, (char)(current[first] + current[second] - ' '));
			queue.add(candidate);
		}
		temp = current[first];
		current[first] = current[second];
		current[second] = temp;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		String key = in.readString() + " ";
		if (!last.containsKey(key)) {
			out.printLine(testNumber, "NO SOLUTION");
			return;
		}
		StringBuilder sequence = new StringBuilder();
		while (last.get(key) != ' ') {
			char c = last.get(key);
			sequence.append(c);
			int first = -1;
			int second = -1;
			for (int i = 0; i < 7; i++) {
				if (key.charAt(i) == ' ' || key.charAt(i) == c) {
					if (first == -1)
						first = i;
					else
						second = i;
				}
			}
			key = key.substring(0, first) + key.charAt(second) + key.substring(first + 1, second) + key.charAt(first) +
				key.substring(second + 1);
		}
		out.printLine(testNumber, sequence.length(), sequence);
	}
}
