package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] width = IOUtils.readIntArray(in, count);
		int[] sums = new int[count + 1];
		for (int i = 0; i < count; i++)
			sums[i + 1] = sums[i] + width[i];
		int[] next = new int[count];
		int[] queue = new int[count];
		int[] barrier = new int[count];
		int j = 0;
		int size = 0;
		int current = count;
		for (int i = count - 1; i >= 0; i--) {
			while (j < size && sums[i] <= barrier[queue[j]])
				current = queue[j++];
			barrier[i] = sums[i] * 2 - sums[current];
			next[i] = current;
			while (size > j && barrier[queue[size - 1]] <= barrier[i])
				size--;
			queue[size++] = i;
		}
		int answer = 0;
		int i = 0;
		while (i != count) {
			answer++;
			i = next[i];
		}
		out.printLine(answer);
	}
}
