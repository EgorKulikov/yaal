package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] matrix = IOUtils.readIntTable(in, size, size);
		int answer = 0;
		for (int i = 0; i < size; i++)
			answer += matrix[i][i];
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			int type = in.readInt();
			if (type == 3) {
				out.print(answer & 1);
			} else {
				int at = in.readInt() - 1;
				answer -= matrix[at][at];
				matrix[at][at] = 1 - matrix[at][at];
				answer += matrix[at][at];
			}
		}
		out.printLine();
	}
}
