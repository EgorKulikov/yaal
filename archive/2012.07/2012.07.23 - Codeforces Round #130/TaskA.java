package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String song = in.readString();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < song.length(); i++) {
			if (song.substring(i).startsWith("WUB")) {
				if (result.length() > 0 && result.charAt(result.length() - 1) != ' ')
					result.append(' ');
				i += 2;
				continue;
			}
			result.append(song.charAt(i));
		}
		out.printLine(result.toString().trim());
	}
}
