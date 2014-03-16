package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] matrix = IOUtils.readIntTable(in, count, count);
		int base = -1;
		for (int i = 0; i < count; i++) {
			if (matrix[i][i] > 0) {
				base = i;
				break;
			}
		}
		int[] queue = new int[count];
		int size = 1;
		boolean[] found = new boolean[count];
		found[base]  = true;
		queue[0] = base;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j = 0; j < count; j++) {
				if (matrix[current][j] > 0 && !found[j]) {
					found[j] = true;
					queue[size++] = j;
				}
			}
		}
		if (size != count) {
			out.printLine("NO");
			return;
		}
		size = 1;
		Arrays.fill(found, false);
		found[base]  = true;
		queue[0] = base;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j = 0; j < count; j++) {
				if (matrix[j][current] > 0 && !found[j]) {
					found[j] = true;
					queue[size++] = j;
				}
			}
		}
		if (size != count) {
			out.printLine("NO");
			return;
		}
		out.printLine("YES");
    }
}
