package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Accordeon {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = read(in);
		String second = read(in);
		out.printLine(first.equals(second) ? "YES" : "NO");
    }

	private String read(InputReader in) {
		String s = in.readLine(false);
		boolean space = false;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				if (space)
					builder.append(' ');
				space = false;
				builder.append(Character.toLowerCase(s.charAt(i)));
			} else
				space = true;
		}
		if (space)
			builder.append(' ');
		return builder.toString().trim();
	}
}
