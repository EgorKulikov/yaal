package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] profit = IOUtils.readIntArray(in, count);
		int negative = 0;
		int total = 0;
		List<Integer> answer = new ArrayList<Integer>();
		for (int i : profit) {
			if (i < 0) {
				if (negative == 2) {
					answer.add(total);
					total = 0;
					negative = 0;
				}
				negative++;
			}
			total++;
		}
		answer.add(total);
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}
}
