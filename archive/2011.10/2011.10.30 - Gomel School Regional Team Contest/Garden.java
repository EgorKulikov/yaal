package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class Garden {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int height = in.readInt();
		long[] start = new long[count];
		long[] delta = new long[count];
		IOUtils.readLongArrays(in, start, delta);
		boolean exact = true;
		for (int i = 0; i < count; i++) {
			long lastHeight = start[i] + delta[i] * (length - 1);
			if (lastHeight < height) {
				out.println(-1);
				return;
			}
			if (lastHeight > height)
				exact = false;
		}
		if (exact)
			out.println(0);
		else
			out.println(1);
	}
}
