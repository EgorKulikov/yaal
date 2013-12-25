package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[][] path = new int[edgeCount][];
		int[][] reversePath = new int[edgeCount][];
		for (int i = 0; i < edgeCount; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
			int length = in.readInt();
			path[i] = IOUtils.readIntArray(in, length);
			reversePath[i] = path[i].clone();
			ArrayUtils.reverse(reversePath[i]);
		}
		MiscUtils.decreaseByOne(path);
		MiscUtils.decreaseByOne(reversePath);
		int[][] id = new int[count][count];
		ArrayUtils.fill(id, -1);
		for (int i = 0; i < edgeCount; i++)
			id[from[i]][to[i]] = i;
		int[][] backId = new int[count][count];
		ArrayUtils.fill(backId, -1);
		for (int i = 0; i < edgeCount; i++)
			backId[to[i]][from[i]] = i;
		int[] queue = new int[2 * count];
		int[] backQueue = new int[2 * count];
		for (int i = 0; i < edgeCount; i++) {
			if (path[i].length > 2 * count)
				continue;
			for (int j = 1; j < path[i].length; j++) {
				if (path[i][j] == to[i] && path[i][j - 1] == from[i]) {
					int length = 0;
					for (int k = j; k < path[i].length; k++)
						queue[length++] = path[i][k];
					length = process(length, queue, from, to, path, id);
					if (length > 2 * count)
						continue;
					int startLength = 0;
					for (int k = j - 1; k >= 0; k--)
						backQueue[startLength++] = path[i][k];
					startLength = process(startLength, backQueue, to, from, reversePath, backId);
					if (length + startLength <= 2 * count) {
						int[] answer = new int[length + startLength];
						for (int k = 0; k < startLength; k++)
							answer[k] = backQueue[startLength - k - 1] + 1;
						for (int k = 0; k < length; k++)
							answer[k + startLength] = queue[k] + 1;
						out.printLine(answer.length);
						out.printLine(answer);
						return;
					}
				}
			}
		}
		out.printLine(0);
    }

	private int process(int length, int[] queue, int[] from, int[] to, int[][] path, int[][] id) {
		int current = queue[0];
		for (int i = 1; i < length; i++) {
			int next = queue[i];
			int edge = id[current][next];
			if (edge == -1 || length + path[edge].length > queue.length)
				return queue.length + 1;
			for (int j : path[edge])
				queue[length++] = j;
			current = next;
		}
		return length;
	}
}
