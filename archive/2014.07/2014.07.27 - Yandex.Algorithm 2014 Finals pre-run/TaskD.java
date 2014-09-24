package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		int[] qty = new int[1000000];
		for (int i : lengths) {
			qty[i - 1]++;
		}
		int nonZero = 1000000 - ArrayUtils.count(qty, 0);
		int answer;
		if (nonZero < 22) {
			for (int i = 0; ; i++) {
				int capacity = 1 << (nonZero + i - 1);
				int need = 0;
				for (int j : qty) {
					need += (j + capacity - 1) >> (nonZero + i - 1);
				}
				if (need <= nonZero + i) {
					answer = nonZero + i;
					break;
				}
			}
		} else {
			answer = nonZero;
		}
		out.printLine(answer);
    }
}
