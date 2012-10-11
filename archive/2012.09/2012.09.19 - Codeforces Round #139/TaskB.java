package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int toSum = in.readInt();
		int[] numbers = new int[100];
		numbers[0] = 1;
		int count;
		for (count = 1; numbers[count - 1] < number; count++) {
			for (int j = count - 1; j >= 0 && j >= count - toSum; j--)
				numbers[count] += numbers[j];
		}
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = count - 1; i >= 0; i--) {
			if (number >= numbers[i]) {
				answer.add(numbers[i]);
				number -= numbers[i];
			}
		}
		answer.add(0);
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}
}
