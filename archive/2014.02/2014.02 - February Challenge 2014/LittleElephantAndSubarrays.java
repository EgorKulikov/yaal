package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndSubarrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int query = in.readInt();
			int answer = 0;
			for (int j = 0; j < count; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = j; k < count; k++) {
					min = Math.min(min, array[k]);
					if (min == query)
						answer++;
				}
			}
			out.printLine(answer);
		}
    }
}
