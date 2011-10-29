package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskE {
	private long[] fibonacci = IntegerUtils.generateFibonacci(10000000000L);
	private String[] bfs;

	{
		bfs = new String[IntegerUtils.generateFibonacci(1000000).length];
		bfs[0] = "0";
		bfs[1] = "1";
		for (int i = 2; i < bfs.length; i++)
			bfs[i] = bfs[i - 2] + bfs[i - 1];
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int index = in.readInt();
		int from = in.readInt();
		int to = in.readInt() + 1;
		if (index >= fibonacci.length) {
			if (index % 2 == fibonacci.length % 2)
				index = fibonacci.length - 2;
			else
				index = fibonacci.length - 1;
		}
		out.println(go(index, from, to));
	}

	private String go(int index, long from, long to) {
		if (index < bfs.length)
			return bfs[index].substring((int) from, (int)to);
		if (to <= fibonacci[index - 2])
			return go(index - 2, from, to);
		if (from >= fibonacci[index - 2])
			return go(index - 1, from - fibonacci[index - 2], to - fibonacci[index - 2]);
		return go(index - 2, from, fibonacci[index - 2]) + go(index - 1, 0, to - fibonacci[index - 2]);
	}
}
