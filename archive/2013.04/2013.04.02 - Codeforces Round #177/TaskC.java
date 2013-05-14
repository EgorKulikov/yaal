package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() + 1;
		int[] answer = new int[count];
		while (count != 0) {
			int power = Integer.highestOneBit(count);
			if (power == count) {
				for (int i = 0; i < count; i++)
					answer[i] = (count - 1) ^ i;
				count = 0;
			} else {
				int current = count - power;
				for (int i = power - current; i < count; i++)
					answer[i] = (2 * power - 1) ^ i;
				count -= 2 * current;
			}
		}
		long total = 0;
		for (int i = 0; i < answer.length; i++)
			total += i ^ answer[i];
		out.printLine(total);
		out.printLine(answer);
    }
}
