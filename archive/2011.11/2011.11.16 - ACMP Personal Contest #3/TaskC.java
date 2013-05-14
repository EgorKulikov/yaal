package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] cost = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, cost);
		MiscUtils.decreaseByOne(from, to);
		int count = 1 << vertexCount;
		short[] totalCost = new short[count];
		byte[] last = new byte[count];
		Arrays.fill(totalCost, Short.MAX_VALUE);
		totalCost[1] = 0;
		for (int i = 1; i < count - 1; i++) {
			if (totalCost[i] == Short.MAX_VALUE)
				continue;
			for (int j = 0; j < edgeCount; j++) {
				int key = i + (1 << to[j]);
				if ((i >> from[j] & 1) == 1 && (i >> to[j] & 1) == 0 && totalCost[key] > totalCost[i] + cost[j]) {
					totalCost[key] = (short) (totalCost[i] + cost[j]);
					last[key] = (byte) j;
				}
			}
		}
		out.printLine(totalCost[count - 1], vertexCount - 1);
		int mask = count - 1;
		int[] answer = new int[vertexCount - 1];
		for (int i = 0; i < vertexCount - 1; i++) {
			answer[i] = last[mask] + 1;
			mask -= 1 << to[last[mask]];
		}
		Arrays.sort(answer);
		out.printLine(Array.wrap(answer).toArray());
	}
}
