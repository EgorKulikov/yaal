package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class Problem8 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Set<Integer> set = new HashSet<Integer>();
		while (true) {
			int number = in.readInt();
			if (number == -1)
				throw new UnknownError();
			if (number == 0)
				break;
			set.add(number);
		}
		int answer = 0;
		for (int i : set) {
			if (set.contains(2 * i))
				answer++;
		}
		out.printLine(answer);
    }
}
