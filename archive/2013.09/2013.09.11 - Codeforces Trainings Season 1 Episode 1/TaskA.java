package net.egork;

import net.egork.collections.map.Indexer;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] distance = new int[edgeCount];
		Indexer<String> indexer = new Indexer<String>();
		indexer.get("Park");
		for (int i = 0; i < edgeCount; i++) {
			String sFrom = in.readString();
			String sTo = in.readString();
			from[i] = indexer.get(sFrom) - 1;
			to[i] = indexer.get(sTo) - 1;
			distance[i] = in.readInt();
		}
		int capacity = in.readInt();
		int count = indexer.size() - 1;
		int[][] minDistance = new int[count][count];
		int[] toPark = new int[count];
		ArrayUtils.fill(minDistance, Integer.MAX_VALUE);
		Arrays.fill(toPark, Integer.MAX_VALUE);
		for (int i = 0; i < edgeCount; i++) {
			if (from[i] == -1)
				toPark[to[i]] = Math.min(toPark[to[i]], distance[i]);
			else if (to[i] == -1)
				toPark[from[i]] = Math.min(toPark[from[i]], distance[i]);
			else
				minDistance[from[i]][to[i]] = minDistance[to[i]][from[i]] = Math.min(minDistance[to[i]][from[i]], distance[i]);
		}
		long answer = Long.MAX_VALUE;
		long[] result = new long[1 << count];
		Arrays.fill(result, Long.MAX_VALUE);
		result[result.length - 1] = 0;
		for (int i = result.length - 1; i > 0; i--) {
			if (result[i] == Long.MAX_VALUE)
				continue;
			if (Integer.bitCount(i) <= capacity) {
				long current = result[i];
				for (int j = 0; j < count; j++) {
					if ((i >> j & 1) == 1)
						current += toPark[j];
				}
				answer = Math.min(answer, current);
			}
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 0)
					continue;
				for (int k = 0; k < count; k++) {
					if (j == k || (i >> k & 1) == 0)
						continue;
					result[i - (1 << j)] = Math.min(result[i - (1 << j)], result[i] + minDistance[j][k]);
				}
			}
		}
		out.printLine("Total miles driven:", answer);
    }
}
