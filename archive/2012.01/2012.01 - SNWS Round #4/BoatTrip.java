package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BoatTrip {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		int[] length = new int[count - 1];
		IOUtils.readIntArrays(in, from, to, length);
		MiscUtils.decreaseByOne(from, to);
		int[] degree = new int[count];
		for (int i : from)
			degree[i]++;
		for (int i : to)
			degree[i]++;
		int answer = 0;
		for (int i : length)
			answer += i << 1;
		int maxEdge = 0;
		for (int i = 0; i < count - 1; i++) {
			if (degree[from[i]] == 1 || degree[to[i]] == 1)
				maxEdge = Math.max(maxEdge, length[i]);
		}
		answer -= maxEdge << 1;
		out.printLine(answer);
	}
}
