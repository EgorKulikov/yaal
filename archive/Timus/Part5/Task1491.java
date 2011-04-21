package Timus.Part5;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1491 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int knightCount = in.readInt();
		long[] goldDifference = new long[knightCount + 1];
		for (int i = 0; i <= knightCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			int delta = in.readInt();
			goldDifference[from] += delta;
			goldDifference[to] -= delta;
		}
		long[] result = new long[knightCount];
		long current = 0;
		for (int i = 0; i < knightCount; i++) {
			current += goldDifference[i];
			result[i] = current;
		}
		IOUtils.printCollection(ArrayWrapper.wrap(result), out);
	}
}

