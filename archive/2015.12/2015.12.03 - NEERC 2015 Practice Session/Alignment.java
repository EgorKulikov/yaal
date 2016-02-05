package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Alignment {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<String[]> tokens = new ArrayList<>();
		while (!in.isExhausted()) {
			tokens.add(in.readLine().trim().split("\\s+"));
		}
		int size = tokens.stream().mapToInt(x -> x.length).max().getAsInt();
		int[] lengths = new int[size];
		for (int i = 0; i < size; i++) {
			final int finalI = i;
			lengths[i] = tokens.stream().mapToInt(x -> x.length > finalI ? x[finalI].length() : 0).max().getAsInt() + 1;
		}
		tokens.forEach(x -> {
			for (int i = 0; i < x.length - 1; i++) {
				out.printFormat("%-" + lengths[i] + "s", x[i]);
			}
			out.printLine(x[x.length - 1]);
		});
	}
}
