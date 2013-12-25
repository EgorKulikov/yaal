package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PoloThePenguinAndTheLuckyString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[] number = IOUtils.readCharArray(in, size);
		int[] stack = new int[size];
		int[] level = new int[size];
		long answer = 0;
		int position = 1;
		int length = 0;
		IntervalTree tree = new SumIntervalTree(size);
		for (char c : number) {
			if (c == '4') {
				level[length] = 0;
				stack[length++] = position;
			} else if (c == '7') {
				if (length != 0) {
					--length;
					answer += position - 1 - 2 * tree.query(0, level[length] - 1);
					if (length != 0)
						level[length - 1] = Math.max(level[length - 1], level[length] + 1);
					tree.update(level[length], level[length], 1);
				}
			}
			position++;
		}
		out.printLine(answer);
    }
}
