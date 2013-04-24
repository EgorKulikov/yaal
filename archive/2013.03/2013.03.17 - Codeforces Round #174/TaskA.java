package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long sum = 0;
		int size = 1;
		int[] delta = new int[count + 1];
		int[] value = new int[count + 1];
		for (int i = 0; i < count; i++) {
			int type = in.readInt();
			if (type == 1) {
				int upTo = in.readInt();
				int curDelta = in.readInt();
				delta[upTo - 1] += curDelta;
				sum += upTo * curDelta;
			} else if (type == 2) {
				sum += value[size++] = in.readInt();
			} else {
				sum -= delta[size - 1] + value[size - 1];
				value[size - 1] = 0;
				delta[size - 2] += delta[size - 1];
				delta[size - 1] = 0;
				size--;
			}
			out.printLine((double)sum / size);
		}
    }
}
