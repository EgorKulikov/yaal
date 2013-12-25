package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int reach = in.readInt();
		int veryReach = in.readInt();
		int[] answer = new int[count];
		Arrays.fill(answer, 1);
		if (count == 1) {
			out.printLine(1);
			return;
		}
		if (veryReach == 0) {
			if (reach == count - 1) {
				out.printLine(-1);
				return;
			}
			for (int i = 2; i < 2 + reach; i++)
				answer[i] = answer[i - 1] + 1;
		} else {
			for (int i = 1; i <= veryReach; i++)
				answer[i] = 2 * answer[i - 1];
			for (int i = veryReach + 1; i <= reach + veryReach; i++)
				answer[i] = answer[i - 1] + 1;
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
