package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		Deque<Character> deque = new ArrayDeque<Character>();
		for (char c : s) {
			if (deque.isEmpty() || c <= deque.peek())
				deque.addFirst(c);
			else
				deque.addLast(c);
		}
		for (char c : deque)
			out.print(c);
		out.printLine();
	}
}
