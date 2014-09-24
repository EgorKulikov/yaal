package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] path = in.readString().toCharArray();
		char[] stack = new char[path.length];
		int top = -1;
		for (char c : path) {
			char current = top == -1 ? 0 : stack[top];
			if (current == 'u' && c == 'd' || current == 'd' && c == 'u' ||
				current == 'l' && c == 'r' || current == 'r' && c == 'l')
			{
				top--;
			} else {
				stack[++top] = c;
			}
		}
		if (top == -1) {
			out.printLine("Yes");
		} else {
			out.printLine("No");
		}
    }
}
