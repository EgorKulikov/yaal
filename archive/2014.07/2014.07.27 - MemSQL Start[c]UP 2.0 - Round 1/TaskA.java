package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	String[] names = {"vaporeon", "jolteon", "flareon", "espeon", "umbreon", "leafeon", "glaceon", "sylveon"};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		String s = in.readString();
		for (String name : names) {
			if (name.matches(s)) {
				out.printLine(name);
				return;
			}
		}
		throw new RuntimeException();
    }
}
