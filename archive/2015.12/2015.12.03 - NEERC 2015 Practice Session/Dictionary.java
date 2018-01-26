package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Stack;

public class Dictionary {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Stack<String> stack = new Stack<>();
		while (!in.isExhausted()) {
			String word = in.readString();
			while (!stack.isEmpty() && (stack.peek().length() < stack.size() || !word.startsWith(stack.peek().substring(0, stack.size()))))
				stack.pop();
			for (int i = 0; i < stack.size(); i++) {
				out.print(' ');
			}
			out.printLine(word);
			stack.add(word);
		}
	}
}
