package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int queries = in.readInt();
		int storageCount = in.readInt();
		int maxBarrels = in.readInt();
		int barrels = 0;
		Stack<Integer>[] stacks = new Stack[storageCount];
		for (int i = 0; i < stacks.length; i++)
			stacks[i] = new Stack<Integer>();
		int answer = 0;
		for (int i = 0; i < queries; i++) {
			char type = in.readCharacter();
			int index = in.readInt() - 1;
			int fuelType = in.readInt();
			if (type == '+') {
				if (barrels == maxBarrels) {
					out.printLine("Error");
					return;
				}
				stacks[index].add(fuelType);
				barrels++;
				answer = Math.max(answer, barrels);
			} else {
				if (stacks[index].isEmpty() || stacks[index].peek() != fuelType) {
					out.printLine("Error");
					return;
				}
				stacks[index].pop();
				barrels--;
			}
		}
		for (int i = 0; i < storageCount; i++) {
			if (!stacks[i].empty()) {
				out.printLine("Error");
				return;
			}
		}
		out.printLine(answer);
	}
}
