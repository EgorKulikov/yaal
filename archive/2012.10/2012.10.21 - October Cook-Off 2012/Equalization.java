package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Equalization {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int step = 1;
		int remaining = count;
		IOUtils.readIntArray(in, count);
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		for (int i = 2; i <= count; i++) {
			while (remaining % i == 0) {
				for (int j = 0; j < count; j += step * i) {
					for (int k = 0; k < step; k++) {
						List<Integer> current = new ArrayList<Integer>();
						for (int l = j + k; l < j + step * i; l += step)
							current.add(l + 1);
						answer.add(current);
					}
				}
				step *= i;
				remaining /= i;
			}
		}
		out.printLine(answer.size());
		for (List<Integer> row : answer) {
			out.print(row.size() + " ");
			out.printLine(row.toArray());
		}
	}
}
