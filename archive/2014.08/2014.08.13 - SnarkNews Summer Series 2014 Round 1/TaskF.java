package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	int[] qty = new int[(int) (1e6 + 2)];

	{
		qty[1] = 1;
		for (int i = 1; i < qty.length - 1; i++) {
			qty[i + 1] = qty[i];
			int copy = i;
			while (copy > 0) {
				if (copy % 10 == 0) {
					qty[i + 1]++;
				}
				copy /= 10;
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		out.printLine(qty[to + 1] - qty[from]);
    }
}
