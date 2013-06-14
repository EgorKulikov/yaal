package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		boolean[][] roads = new boolean[edgeCount][count + 1];
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			roads[i][from] = true;
			roads[i][to] = true;
			roads[i][count] = in.readInt() == 0;
		}
		boolean[] taken = new boolean[edgeCount];
		for (int i = 0; i < count; i++) {
			int index = -1;
			for (int j = 0; j < edgeCount; j++) {
				if (!taken[j] && roads[j][i]) {
					index = j;
					break;
				}
			}
			if (index == -1)
				continue;
			taken[index] = true;
			for (int j = count; j >= i; j--) {
				if (roads[index][j]) {
					for (int k = 0; k < edgeCount; k++) {
						if (k != index && roads[k][i])
							roads[k][j] = !roads[k][j];
					}
				}
			}
		}
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < edgeCount; i++) {
			if (roads[i][count]) {
				boolean found = false;
				for (int j = 0; j < count; j++) {
					if (roads[i][j]) {
						answer.add(j + 1);
						found = true;
						break;
					}
				}
				if (!found) {
					out.printLine("Impossible");
					return;
				}
			}
		}
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}
}
