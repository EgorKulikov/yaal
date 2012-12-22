package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ParallelComputing {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		List<List<String>> answer = new ArrayList<List<String>>();
		int i;
		for (i = 1; 2 * i <= count; i *= 2) {
			List<String> current = new ArrayList<String>();
			for (int j = 2 * i; j <= count; j += 2 * i)
				current.add((j - i) + "+" + j + "=" + j);
			answer.add(current);
		}
		while (3 * i > count)
			i /= 2;
		for (; i != 0; i /= 2) {
			List<String> current = new ArrayList<String>();
			for (int j = 2 * i; j + i <= count; j += 2 * i)
				current.add(j + "+" + (j + i) + "=" + (j + i));
			answer.add(current);
		}
		out.printLine(answer.size());
		for (List<String> row : answer) {
			out.print(row.size() + " ");
			out.printLine(row.toArray());
		}
	}
}
