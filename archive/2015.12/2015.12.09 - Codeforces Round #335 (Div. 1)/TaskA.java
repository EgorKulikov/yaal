package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] p = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(p);
		int[] rev = ArrayUtils.reversePermutation(p);
		int length = 0;
		int answer = 0;
		int last = -1;
		for (int i : rev) {
			if (i > last) {
				length++;
			} else {
				length = 1;
			}
			last = i;
			answer = Math.max(answer, length);
		}
		out.printLine(n - answer);
	}
}
