package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		char[][] positions = IOUtils.readTable(in, count, length);
		sort(0, count - 1, "ABC", length - 1, positions);
		for (char[] row : positions)
			out.printLine(row);
    }

	private void sort(int from, int to, final String abc, final int index, char[][] positions) {
		if (from == to)
			return;
		Arrays.sort(positions, from, to + 1, new Comparator<char[]>() {
			public int compare(char[] o1, char[] o2) {
				return abc.indexOf(o1[index]) - abc.indexOf(o2[index]);
			}
		});
		for (int i = from; i <= to; i++) {
			if (positions[i][index] != abc.charAt(0)) {
				if (i != from)
					sort(from, i - 1, "" + abc.charAt(0) + abc.charAt(2) + abc.charAt(1), index - 1, positions);
				sort(i, to, "" + abc.charAt(2) + abc.charAt(1) + abc.charAt(0), index - 1, positions);
				return;
			}
		}
		sort(from, to, "" + abc.charAt(0) + abc.charAt(2) + abc.charAt(1), index - 1, positions);
	}
}
