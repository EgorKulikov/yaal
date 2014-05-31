package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int capacity = in.readInt();
		int[] sizes = IOUtils.readIntArray(in, count);
		Arrays.sort(sizes);
		boolean[] used = new boolean[count];
		int at = count - 1;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (used[i])
				continue;
			while (at > i && sizes[i] + sizes[at] > capacity)
				at--;
			if (at > i)
				used[at--] = true;
			used[i] = true;
			answer++;
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
