package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		int[][] next = new int[20][count + 1];
		ArrayUtils.fillColumn(next, count, count);
		int j = count;
		int length = 0;
		int answer = 0;
		int index = count;
		for (int i = count - 1; i >= 0; i--) {
			length += words[i].length();
			while (length + j - i - 1 > columnCount)
				length -= words[--j].length();
			next[0][i] = j;
			for (int k = 1; k < 20; k++)
				next[k][i] = next[k - 1][next[k - 1][i]];
			int l = i;
			for (int k = 0; k < 20; k++) {
				if ((rowCount >> k & 1) == 1)
					l = next[k][l];
			}
			if (l - i > answer) {
				answer = l - i;
				index = i;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			boolean first = true;
			for (int k = index; k < next[0][index]; k++) {
				if (first)
					first = false;
				else
					out.print(' ');
				out.print(words[k]);
			}
			out.printLine();
			index = next[0][index];
		}
    }
}
