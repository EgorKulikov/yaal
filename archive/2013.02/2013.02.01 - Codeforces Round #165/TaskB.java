package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int typeCount = in.readInt();
		int[] type = new int[count];
		for (int i = 0; i < count; i++) {
			type[i] = in.readInt() - 1;
			in.readDouble();
		}
		int[] answer = new int[typeCount ];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < typeCount; j++) {
				if (type[i] != j)
					answer[j]++;
			}
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < typeCount; j++) {
				min = Math.min(min, answer[j]);
				answer[j] = Math.min(answer[j], min);
			}
		}
		out.printLine(answer[typeCount - 1]);
    }
}
