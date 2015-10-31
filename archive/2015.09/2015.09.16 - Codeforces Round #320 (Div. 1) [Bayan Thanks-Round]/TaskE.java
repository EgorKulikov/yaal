package net.egork;

import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] next = new int[s.length];
		int[] length = new int[s.length];
		char[] end = new char[s.length];
		Heap rs = new Heap((a, b) -> length[b] - length[a], s.length);
		Heap ls = new Heap((a, b) -> length[b] - length[a], s.length);
		Arrays.fill(next, -1);
		for (int i = s.length - 1; i >= 0; i--) {
			if (s[i] == 'R') {
				if (!ls.isEmpty()) {
					next[i] = ls.poll();
					length[i] = length[next[i]] + 1;
					end[i] = end[next[i]];
				} else {
					length[i] = 1;
					end[i] = 'L';
				}
				rs.add(i);
			} else {
				if (!rs.isEmpty()) {
					next[i] = rs.poll();
					length[i] = length[next[i]] + 1;
					end[i] = end[next[i]];
				} else {
					length[i] = 1;
					end[i] = 'R';
				}
				ls.add(i);
			}
		}
		int rCount = ArrayUtils.count(s, 'R');
		int lCount = s.length - rCount;
		IntList ll = new IntArrayList();
		IntList lr = new IntArrayList();
		IntList rl = new IntArrayList();
		IntList rr = new IntArrayList();
		while (!ls.isEmpty()) {
			int current = ls.poll();
			if (end[current] == 'R') {
				lr.add(current);
			} else {
				ll.add(current);
			}
		}
		while (!rs.isEmpty()) {
			int current = rs.poll();
			if (end[current] == 'L') {
				rl.add(current);
			} else {
				rr.add(current);
			}
		}
		out.printLine(ll.size() + lr.size() + rl.size() + rr.size() - 1);
		IntList answer = new IntArrayList();
		if (lr.size() == 0 && rl.size() == 0) {
			if (ll.size() != 0 && rr.size() != 0) {
				throw new RuntimeException();
			}
			for (int i : ll.toArray()) {
				add(answer, next, i);
			}
			for (int i : rr.toArray()) {
				add(answer, next, i);
			}
		} else {
			if (lr.size() > rl.size()) {
				construct(next, ll, lr, rl, rr, answer);
			} else {
				construct(next, rr, rl, lr, ll, answer);
			}
		}
		out.printLine(answer);
	}

	protected void construct(int[] next, IntList ll, IntList lr, IntList rl, IntList rr, IntList answer) {
		for (int i : ll.toArray()) {
			add(answer, next, i);
		}
		add(answer, next, lr.popBack());
		for (int i : rr.toArray()) {
			add(answer, next, i);
		}
		while (true) {
			if (rl.size() == 0) {
				break;
			}
			add(answer, next, rl.popBack());
			if (lr.size() == 0) {
				break;
			}
			add(answer, next, lr.popBack());
		}
	}

	private void add(IntList answer, int[] next, int current) {
		while (current != -1) {
			answer.add(current + 1);
			current = next[current];
		}
	}
}
