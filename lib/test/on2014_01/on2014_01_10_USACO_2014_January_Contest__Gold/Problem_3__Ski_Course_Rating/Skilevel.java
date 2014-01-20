package on2014_01.on2014_01_10_USACO_2014_January_Contest__Gold.Problem_3__Ski_Course_Rating;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Skilevel {
	long answer;
	int curDelta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int rowCount = in.readInt();
		final int columnCount = in.readInt();
		final int required = in.readInt();
		int[][] map = IOUtils.readIntTable(in, rowCount, columnCount);
		final int[][] start = IOUtils.readIntTable(in, rowCount, columnCount);
		if (required == 1) {
			out.printLine(0);
			return;
		}
		final int[][] size = new int[rowCount][columnCount];
		ArrayUtils.fill(size, 1);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(rowCount * columnCount);
		setSystem.setListener(new IndependentSetSystem.Listener() {
			public void joined(int joinedRoot, int root) {
				int joinedRow = joinedRoot / columnCount;
				int joinedColumn = joinedRoot % columnCount;
				int row = root / columnCount;
				int column = root % columnCount;
				if (size[row][column] + size[joinedRow][joinedColumn] >= required) {
					if (size[row][column] < required)
						answer += (long)curDelta * start[row][column];
					if (size[joinedRow][joinedColumn] < required)
						answer += (long)curDelta * start[joinedRow][joinedColumn];
				}
				size[row][column] += size[joinedRow][joinedColumn];
				start[row][column] += start[joinedRow][joinedColumn];
			}
		});
		List<Edge> list = new ArrayList<Edge>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int key = i * columnCount + j;
				if (i + 1 < rowCount)
					list.add(new Edge(key, key + columnCount, Math.abs(map[i][j] - map[i + 1][j])));
				if (j + 1 < columnCount)
					list.add(new Edge(key, key + 1, Math.abs(map[i][j] - map[i][j + 1])));
			}
		}
		Collections.sort(list);
		for (Edge edge : list) {
			curDelta = edge.delta;
			setSystem.join(edge.from, edge.to);
		}
		out.printLine(answer);
    }

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int delta;

		Edge(int from, int to, int delta) {
			this.from = from;
			this.to = to;
			this.delta = delta;
		}

		public int compareTo(Edge o) {
			return delta - o.delta;
		}
	}
}
