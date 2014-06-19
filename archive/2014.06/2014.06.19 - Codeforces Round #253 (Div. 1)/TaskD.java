package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(parent);
		int[] value = new int[count + 1];
		int[] answer = new int[count];
		value[0] = 0;
		int[] max = new int[count + 1];
		Arrays.fill(max, -1);
		for (int i = 1; i <= count; i++) {
			int current = i;
			value[i] = 0;
			int up = 1;
			while (current != 0) {
				int next = parent[current - 1];
				if (value[next] < up) {
					value[next] = up;
					max[next] = current;
				} else if (value[next] == up && current != max[next]) {
					up++;
				} else {
					break;
				}
				current = next;
			}
			answer[i - 1] = value[0];
		}
		out.printLine(answer);
    }
}
