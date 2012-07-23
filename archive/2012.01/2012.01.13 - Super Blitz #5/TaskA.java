package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Stack<Integer> stack = new Stack<Integer>();
		while (!in.isExhausted()) {
			String operand = in.readString();
			if (Character.isDigit(operand.charAt(0)))
				stack.add(Integer.parseInt(operand));
			else {
				if ("+".equals(operand)) {
					int first = stack.pop();
					int second = stack.pop();
					stack.add(second + first);
				} else if ("-".equals(operand)) {
					int first = stack.pop();
					int second = stack.pop();
					stack.add(second - first);
				} else if ("*".equals(operand)) {
					int first = stack.pop();
					int second = stack.pop();
					stack.add(second * first);
				}
			}
		}
		out.printLine(stack.peek());
	}
}
