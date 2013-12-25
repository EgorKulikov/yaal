package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefTownParade {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int count = in.readInt();
		int length = in.readInt();
		if (length == 1) {
			out.printLine(count);
			return;
		}
		long[] levels = IOUtils.readLongArray(in, count);
		int[] min = new int[count];
		int[] max = new int[count];
		int minLength = 1;
		int maxLength = 1;
		int minStart = 0;
		int maxStart = 0;
		for (int i = 1; i < length; i++) {
			while (minLength > minStart && levels[i] < levels[min[minLength - 1]])
				minLength--;
			min[minLength++] = i;
			while (maxLength > maxStart && levels[i] > levels[max[maxLength - 1]])
				maxLength--;
			max[maxLength++] = i;
		}
		int answer = 0;
		if (levels[max[maxStart]] - levels[min[minStart]] == length - 1)
			answer++;
		for (int i = length; i < count; i++) {
			if (max[maxStart] == i - length)
				maxStart++;
			if (min[minStart] == i - length)
				minStart++;
			while (minLength > minStart && levels[i] < levels[min[minLength - 1]])
				minLength--;
			min[minLength++] = i;
			while (maxLength > maxStart && levels[i] > levels[max[maxLength - 1]])
				maxLength--;
			max[maxLength++] = i;
			if (levels[max[maxStart]] - levels[min[minStart]] == length - 1)
				answer++;
		}
		out.printLine(answer);
	}
}
