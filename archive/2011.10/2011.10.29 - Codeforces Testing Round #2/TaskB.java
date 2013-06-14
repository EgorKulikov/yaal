package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String text = in.readString();
		int delta = 0;
		int index = 0;
		while (index < text.length()) {
			if (text.charAt(index + 2) == '>') {
				out.println(space(delta++) + text.substring(index, index + 3));
				index += 3;
			} else {
				out.println(space(--delta) + text.substring(index, index + 4));
				index += 4;
			}
		}
	}

	private String space(int length) {
		StringBuilder builder = new StringBuilder(2 * length);
		for (int i = 0; i < 2 * length; i++)
			builder.append(' ');
		return builder.toString();
	}
}
