package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String colors = in.readString();
		String answer = "";
		for (int i = 0; i < colors.length(); i++) {
			for (int j = 0; j <= i && i + j < colors.length(); j++) {
				if (colors.charAt(i - j) != colors.charAt(i + j))
					break;
				if (answer.length() < 2 * j + 1)
					answer = colors.substring(i - j, i + j + 1);
			}
		}
		for (int i = 1; i < colors.length(); i++) {
			for (int j = 1; j <= i && i + j <= colors.length(); j++) {
				if (colors.charAt(i - j) != colors.charAt(i + j - 1))
					break;
				if (answer.length() < 2 * j)
					answer = colors.substring(i - j, i + j);
			}
		}
		out.printLine(answer);
	}
}
