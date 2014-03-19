package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndWatson {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int shift = in.readInt() % count;
		int queryCount = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		for (int i = 0; i < queryCount; i++) {
			int query = in.readInt();
			out.printLine(array[(query + count - shift) % count]);
		}
    }
}
