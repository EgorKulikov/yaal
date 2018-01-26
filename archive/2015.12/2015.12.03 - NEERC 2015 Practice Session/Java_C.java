package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Java_C {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if (s.contains("_")) {
			if (s.matches(".*[A-Z].*") || s.contains("__") || s.startsWith("_") || s.endsWith("_")) {
				out.printLine("Error!");
				return;
			}
			boolean upper = false;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '_')
					upper = true;
				else if (upper) {
					out.print(Character.toUpperCase(c));
					upper = false;
				} else
					out.print(c);
			}
		} else {
			if (Character.isUpperCase(s.charAt(0))) {
				out.printLine("Error!");
				return;
			}
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (Character.isUpperCase(c)) {
					out.print('_');
					out.print(Character.toLowerCase(c));
				} else
					out.print(c);
			}
		}
		out.printLine();
	}
}
