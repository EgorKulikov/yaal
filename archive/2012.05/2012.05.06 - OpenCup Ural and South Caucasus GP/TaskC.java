package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] number = new int[count];
		for (int i = 0; i < count; i++)
			number[i] = in.readInt();
		int start = -20000;
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		for (int i = -20000; i <= 20000; i++) {
			int result = i;
			for (int j : number)
				result = Math.abs(result - j);
			if (Math.abs(result) > 1) {
				if (start != i) {
					first.add(start);
					second.add(i - 1);
				}
				start = i + 1;
			}
		}
		out.printLine(first.size());
		for (int i = 0; i < first.size(); i++)
			out.printLine(first.get(i), second.get(i));
	}
}
