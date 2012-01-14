package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long money = in.readInt();
		long count = in.readInt();
		if (count < 3) {
			out.printLine("NO SOLUTION");
			return;
		}
		long[] prices = IOUtils.readLongArray(in, 3);
		count -= 3;
		for (long price : prices)
			money -= price;
		long minimum = CollectionUtils.minElement(Array.wrap(prices));
		money -= count * minimum;
		if (money < 0) {
			out.printLine("NO SOLUTION");
			return;
		}
		for (int i = 0; i < 3; i++)
			prices[i] -= minimum;
		long[] answer = new long[3];
		int index1 = -1;
		for (int i = 0; i < 3; i++) {
			if (prices[i] != 0) {
				index1 = i;
				break;
			}
		}
		if (index1 == -1) {
			if (money == 0) {
				out.printLine(1, 1, count + 1);
				return;
			}
			out.printLine("NO SOLUTION");
			return;
		}
		int index2 = -1;
		for (int i = index1 + 1; i < 3; i++) {
			if (prices[i] != 0) {
				index2 = i;
				break;
			}
		}
		if (index2 == -1) {
			if (money % prices[index1] != 0) {
				out.printLine("NO SOLUTION");
				return;
			}
			answer[index1] = money / prices[index1];
			if (answer[index1] > count) {
				out.printLine("NO SOLUTION");
				return;
			}
			if (index1 < 2)
				index2 = 2;
			else
				index2 = 1;
			answer[index2] = count - answer[index1];
			for (int i = 0; i < 3; i++)
				answer[i]++;
			out.printLine(Array.wrap(answer).toArray());
			return;
		}
		long remaining;
		long bestValue = Long.MAX_VALUE;
		long bestIndex = -1;
		for (answer[index1] = 0, remaining = money; remaining >= 0; answer[index1]++, remaining -= prices[index1]) {
			if (remaining % prices[index2] == 0) {
				answer[index2] = remaining / prices[index2];
				answer[3 - index1 - index2] = count - answer[index1] - answer[index2];
				if (answer[3 - index1 - index2] >= 0 && answer[0] < bestValue) {
					bestValue = answer[0];
					bestIndex = answer[index1];
				}
			}
		}
		if (bestValue == Long.MAX_VALUE) {
			out.printLine("NO SOLUTION");
			return;
		}
		answer[index1] = bestIndex;
		answer[index2] = (money - bestIndex * prices[index1]) / prices[index2];
		answer[3 - index1 - index2] = count - answer[index1] - answer[index2];
		for (int i = 0; i < 3; i++)
			answer[i]++;
		out.printLine(Array.wrap(answer).toArray());
	}
}
