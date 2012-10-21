package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class ClosingTheTweets {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int queryCount = in.readInt();
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < queryCount; i++) {
			String command = in.readString();
			if ("CLICK".equals(command)) {
				int index = in.readInt();
				if (set.contains(index))
					set.remove(index);
				else
					set.add(index);
			} else
				set.clear();
			out.printLine(set.size());
		}
	}
}
