package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] sequence = in.readString().toCharArray();
		char[] stack = new char[sequence.length];
		int tail = -1;
		for (char c : sequence) {
			if (tail >= 0 && stack[tail] == c)
				tail--;
			else
				stack[++tail] = c;
		}
		if (tail == -1)
			out.printLine("Yes");
		else
			out.printLine("No");
    }
}
