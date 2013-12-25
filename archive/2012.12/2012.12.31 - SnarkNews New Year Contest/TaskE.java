package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x1 = 2 * in.readInt();
		int y1 = 2 * in.readInt();
		int x2 = 2 * in.readInt();
		int y2 = 2 * in.readInt();
		int diameter = 2 * in.readInt();
		long area = 0;
		int delta = Math.abs(x1 - x2);
		int last = -1;
		for (int y = (int) -4e5; y <= 4e5; y++) {
			int min = Math.abs(y - y1) + Math.abs(y - y2) + delta;
			if (min <= diameter) {
				if (last != -1)
					area += delta + Math.abs(diameter - min) + last;
				last = delta + Math.abs(diameter - min);
			}
		}
		out.printLine(area / 8d);
    }
}
