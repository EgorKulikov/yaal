package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] sequences = new int[count][];
		for (int i = 0; i < count; i++)
			sequences[i] = IOUtils.readIntArray(in, in.readInt());
		int[] next = Arrays.copyOf(sequences[count - 1], sequences[count - 1].length + 1);
		next[next.length - 1] = Integer.MAX_VALUE;
		for (int i = count - 2; i >= 0; i--) {
			int[] current = new int[sequences[i].length + 1];
			int j = 0;
			for (int k = 0; k < sequences[i].length; k++) {
				while (j < next.length - 1 && sequences[i][k] > sequences[i + 1][j])
					j++;
				current[k] = next[j];
			}
			current[current.length - 1] = Integer.MAX_VALUE;
			next = current;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < sequences[0].length; i++) {
			if (next[i] == Integer.MAX_VALUE)
				break;
			answer = Math.min(answer, next[i] - sequences[0][i]);
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		out.printLine(answer);
    }
}
