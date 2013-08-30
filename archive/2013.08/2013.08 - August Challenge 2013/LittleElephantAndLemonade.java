package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LittleElephantAndLemonade {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] path = IOUtils.readIntArray(in, length);
		int[][] bottles = new int[count][];
		int[] at = new int[count];
		for (int i = 0; i < count; i++) {
			bottles[i] = IOUtils.readIntArray(in, in.readInt());
			Arrays.sort(bottles[i]);
			at[i] = bottles[i].length - 1;
		}
		int answer = 0;
		for (int i : path) {
			if (at[i] >= 0)
				answer += bottles[i][at[i]--];
		}
		out.printLine(answer);
    }
}
