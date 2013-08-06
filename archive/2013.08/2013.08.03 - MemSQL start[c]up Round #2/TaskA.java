package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] required = in.readString().toCharArray();
		int length = in.readInt();
		final int[] qty = new int[26];
		for (char c : required)
			qty[c - 'a']++;
		final int[] taken = new int[26];
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return taken[first] * qty[second] - qty[first] * taken[second];
			}
		}, 26);
		for (int i = 0; i < 26; i++) {
			if (qty[i] != 0)
				heap.add(i);
		}
		if (length < heap.getSize()) {
			out.printLine(-1);
			return;
		}
		StringBuilder answer = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int current = heap.poll();
			answer.append((char)('a' + current));
			taken[current]++;
			heap.add(current);
		}
		int max = 0;
		for (int i = 0; i < 26; i++) {
			if (qty[i] != 0)
				max = Math.max(max, (qty[i] - 1) / taken[i] + 1);
		}
		out.printLine(max);
		out.printLine(answer);
    }
}
