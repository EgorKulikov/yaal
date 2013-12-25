package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		boolean[] rows = new boolean[size];
		boolean[] columns = new boolean[size];
		rows[0] = rows[size - 1] = true;
		columns[0] = columns[size - 1] = true;
		for (int i = 0; i < count; i++) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			rows[row] = true;
			columns[column] = true;
		}
		if ((size & 1) == 1 && !rows[size >> 1])
			columns[size >> 1] = true;
		int answer = CollectionUtils.count(Array.wrap(rows), false) + CollectionUtils.count(Array.wrap(columns), false);
		out.printLine(answer);
    }
}
