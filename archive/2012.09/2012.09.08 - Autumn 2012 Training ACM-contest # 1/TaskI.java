package net.egork;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		List<Pair<Long,Integer>> factorize = IntegerUtils.factorize(number);
		boolean first = true;
		for (Pair<Long, Integer> pair : factorize) {
			for (int j = 0; j < pair.second; j++) {
				if (first)
					first = false;
				else
					out.print(" * ");
				out.print(pair.first);
			}
		}
		out.printLine();
	}
}
