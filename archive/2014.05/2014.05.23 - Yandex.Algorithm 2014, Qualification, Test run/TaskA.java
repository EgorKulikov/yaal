package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] input = in.readLine().toCharArray();
		StringBuilder builder = new StringBuilder();
		StringBuilder currentSmile = new StringBuilder();
		for (char c : input) {
			if (Character.isLetter(c)) {
				if (currentSmile.length() > 0 && currentSmile.charAt(0) == '(')
					currentSmile.append(c);
				else {
					builder.append(currentSmile);
					currentSmile.delete(0, currentSmile.length());
					builder.append(c);
				}
			} else if (Character.isDigit(c) || c == ' ') {
				builder.append(currentSmile);
				currentSmile.delete(0, currentSmile.length());
				builder.append(c);
			} else if (c == ')') {
				if (currentSmile.length() > 1 || currentSmile.length() == 1 && currentSmile.charAt(0) != '(') {
					builder.append("<S>");
					builder.append(currentSmile);
					builder.append(c);
					builder.append("</S>");
					currentSmile.delete(0, currentSmile.length());
				} else {
					builder.append(currentSmile);
					currentSmile.delete(0, currentSmile.length());
					builder.append(c);
				}
			} else {
				builder.append(currentSmile);
				currentSmile.delete(0, currentSmile.length());
				currentSmile.append(c);
			}
		}
		builder.append(currentSmile);
		out.printLine(builder);
    }
}
