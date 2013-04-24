package net.egork;

import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Counter<Integer> counter = new Counter<Integer>();
		for (int i = 0; i < count; i++) {
			int id = in.readInt();
			if (id != 0)
				counter.add(id);
		}
		int answer = 0;
		for (long l : counter.values()) {
			if (l > 2) {
				answer = -1;
				break;
			}
			if (l == 2)
				answer++;
		}
		out.printLine(answer);
    }
}
