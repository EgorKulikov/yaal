package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GandalfVsTheBalrog {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int reverseCount = in.readInt();
		int[] from = new int[reverseCount];
		int[] to = new int[reverseCount];
		IOUtils.readIntArrays(in, to, from);
		for (int i = 0; i < reverseCount; i++) {
			from[i] = count - from[i];
			to[i] = count - to[i];
		}
		int[] up = new int[count];
		int[] down = new int[count];
		for (int i = 0; i < reverseCount; i++) {
			up[to[i]]++;
			down[from[i]]++;
		}
		for (int i = 0; i < count; i++) {
			if (up[i] == i && down[i] == 0) {
				out.printLine(2, count - i);
				return;
			}
		}
		out.printLine(1);
	}
}
