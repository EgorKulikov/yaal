package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String message = in.readString();
		if (message.length() % 2 != 1) {
			out.printLine("Suspicious!");
			return;
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < message.length(); i += 2) {
			answer.append(message.charAt(i));
			if (i + 1 < message.length() && message.charAt(i + 1) != 'a') {
				out.printLine("Suspicious!");
				return;
			}
		}
		out.printLine(answer);
	}
}

