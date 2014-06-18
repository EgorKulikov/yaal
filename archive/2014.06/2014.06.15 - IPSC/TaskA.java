package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String password = in.readString();
		String input = in.readString();
		int commonPrefix = 0;
		for (int i = 1; i <= Math.min(password.length(), input.length()); i++) {
			if (password.substring(0, i).equals(input.substring(0, i)))
				commonPrefix = i;
			else
				break;
		}
		if (input.length() - commonPrefix < commonPrefix + 1) {
			for (int i = 0; i < input.length() - commonPrefix; i++)
				out.print('<');
			out.print(password.substring(commonPrefix));
			out.printLine('*');
		} else {
			out.printLine('*' + password + '*');
		}
    }
}
