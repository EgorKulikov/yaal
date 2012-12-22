package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxSum = in.readInt();
		int[] value = new int[count];
		for (int i = 0; i < count; i++)
			value[i] = in.readInt();
		Arrays.sort(value);
		int start = 0;
		int end = count - 1;
		int index = 0;
		Integer[] answer = new Integer[count];
		int countTrips = 0;
		boolean[] taken = new boolean[count];
		while (true) {
			while (start < end && value[start] + value[end] <= maxSum)
				start++;
			if (start >= end)
				break;
			answer[index++] = value[start];
			answer[index++] = value[end];
			countTrips += 2;
			taken[start++] = true;
			taken[end--] = true;
		}
		int curIndex = index;
		for (int i = count - 1; i >= 0; i--) {
			if (!taken[i]) {
				answer[index++] = value[i];
			}
		}
		if (curIndex != 0 && curIndex != count && answer[curIndex] + answer[curIndex - 1] <= maxSum)
			curIndex++;
		countTrips += (count - curIndex + 1) / 2;
		out.printLine(countTrips);
		out.printLine(answer);
	}
}
