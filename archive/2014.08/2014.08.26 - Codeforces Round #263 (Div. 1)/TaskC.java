package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(count);
		for (int i = 0; i < count; i++) {
			tree.update(i, i, 1);
		}
		int start = 0;
		int direction = 1;
		int length = count;
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int by = in.readInt();
				if (2 * by > length) {
					for (int j = 0; j < length - by; j++) {
						tree.update(start + direction * by - (j + 1) * direction,
							start + direction * by - (j + 1) * direction,
							tree.query(start + direction * (j + by), start + direction * (j + by)));
					}
					start += (by - 1) * direction;
					length = by;
					direction = -direction;
				} else {
					for (int j = 0; j < by; j++) {
						tree.update(start + (j + by) * direction, start + (j + by) * direction,
							tree.query(start + (by - j - 1) * direction, start + (by - j - 1) * direction));
					}
					start += by * direction;
					length -= by;
				}
			} else {
				int from = in.readInt();
				int to = in.readInt() - 1;
				out.printLine(tree.query(start + Math.min(from * direction, to * direction),
					start + Math.max(from * direction, to * direction)));
			}
		}
    }
}
