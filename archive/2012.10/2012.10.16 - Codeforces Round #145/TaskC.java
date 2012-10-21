package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = Integer.bitCount(Integer.highestOneBit(count - 1) - 1) + 1;
		out.printLine(answer);
		for (int i = 0; i < answer; i++) {
			List<Integer> current = new ArrayList<Integer>();
			for (int j = 0; j < count; j++) {
				if ((j >> i & 1) == 0)
					current.add(j + 1);
			}
			out.print(current.size() + " ");
			out.printLine(current.toArray());
		}
	}
}
