package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF1 {
	private int rowCount;
		private int columnCount;
		private int[][] distance;
		private int[] malePosition;
		private int[] femalePosition;
		private int[] maleTime;
		private int[] femaleTime;

		public void solve(int testNumber, InputReader in, OutputWriter out) {
			rowCount = in.readInt();
			columnCount = in.readInt();
			int maleCount = in.readInt();
			int femaleCount = in.readInt();
			if (Math.abs(maleCount - femaleCount) != 1) {
				out.printLine(-1);
				return;
			}
			char[][] map = IOUtils.readTable(in, rowCount, columnCount);
			distance = new int[rowCount * columnCount][rowCount * columnCount];
			ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
			int[] queueRow = new int[rowCount * columnCount];
			int[] queueColumn = new int[rowCount * columnCount];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (map[i][j] == '#') {
						continue;
					}
					int id = i * columnCount + j;
					distance[id][id] = 0;
					queueRow[0] = i;
					queueColumn[0] = j;
					int size = 1;
					for (int k = 0; k < size; k++) {
						int row = queueRow[k];
						int column = queueColumn[k];
						for (int l = 0; l < 4; l++) {
							int nRow = row + MiscUtils.DX4[l];
							int nColumn = column + MiscUtils.DY4[l];
							if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] != '#') {
								int nId = nRow * columnCount + nColumn;
								if (distance[id][nId] == Integer.MAX_VALUE / 2) {
									queueRow[size] = nRow;
									queueColumn[size++] = nColumn;
									distance[id][nId] = distance[id][row * columnCount + column] + 1;
								}
							}
						}
					}
	//				for (int k = 0; k < 4; k++) {
	//					int row = i + MiscUtils.DX4[k];
	//					int column = j + MiscUtils.DY4[k];
	//					if (MiscUtils.isValidCell(row, column, rowCount, columnCount) && map[row][column] != '#') {
	//						distance[id][row * columnCount + column] = 1;
	//					}
	//				}
				}
			}
	//		for (int i = 0; i < rowCount * columnCount; i++) {
	//			for (int j = 0; j < rowCount * columnCount; j++) {
	//				for (int k = 0; k < rowCount * columnCount; k++) {
	//					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
	//				}
	//			}
	//		}
			malePosition = new int[Math.max(maleCount, femaleCount)];
			femalePosition = new int[Math.max(maleCount, femaleCount)];
			maleTime = new int[Math.max(maleCount, femaleCount)];
			femaleTime = new int[Math.max(maleCount, femaleCount)];
			if (maleCount < femaleCount) {
				malePosition[maleCount] = read(in);
				maleTime[maleCount] = in.readInt();
			} else {
				femalePosition[femaleCount] = read(in);
				femaleTime[femaleCount] = in.readInt();
			}
			for (int i = 0; i < maleCount; i++) {
				malePosition[i] = read(in);
				maleTime[i] = in.readInt();
			}
			for (int i = 0; i < femaleCount; i++) {
				femalePosition[i] = read(in);
				femaleTime[i] = in.readInt();
			}
			if (!calculate(Long.MAX_VALUE / 2)) {
				out.printLine(-1);
				return;
			}
			long left = 0;
			long right = 23L * 11 * 1000000000;
			while (left < right) {
				long middle = (left * 3 + right) / 4;
				if (calculate(middle)) {
					right = middle;
				} else {
					left = middle + 1;
				}
			}
			out.printLine(left);
		}

	Graph graph = null;
	long last = Long.MAX_VALUE;
	long lastResult;

		private boolean calculate(long time) {
			int source = 2 * malePosition.length + 2 * rowCount * columnCount;
			int sink = source + 1;
			if (time > last) {
				for (int i = 0; i < malePosition.length; i++) {
					for (int j = 0; j < rowCount * columnCount; j++) {
						if (distance[malePosition[i]][j] != Integer.MAX_VALUE / 2 && (long) distance[malePosition[i]][j] * maleTime[i] <= time
							&& (long) distance[malePosition[i]][j] * maleTime[i] > last) {
							graph.addFlowEdge(i, 2 * malePosition.length + j, 1);
						}
						if (distance[femalePosition[i]][j] != Integer.MAX_VALUE / 2 && (long) distance[femalePosition[i]][j] * femaleTime[i] <= time
							&& (long) distance[femalePosition[i]][j] * femaleTime[i] > last) {
							graph.addFlowEdge(2 * malePosition.length + rowCount * columnCount + j, malePosition.length + i, 1);
						}
					}
				}
			} else {
				graph = new Graph(2 * malePosition.length + 2 * rowCount * columnCount + 2);
				lastResult = 0;
				for (int i = 0; i < rowCount * columnCount; i++) {
					graph.addFlowEdge(2 * malePosition.length + i, 2 * malePosition.length + rowCount * columnCount + i, 1);
				}
				for (int i = 0; i < malePosition.length; i++) {
					graph.addFlowEdge(source, i, 1);
					graph.addFlowEdge(i + malePosition.length, sink, 1);
					for (int j = 0; j < rowCount * columnCount; j++) {
						if (distance[malePosition[i]][j] != Integer.MAX_VALUE / 2 && (long) distance[malePosition[i]][j] * maleTime[i] <= time) {
							graph.addFlowEdge(i, 2 * malePosition.length + j, 1);
						}
						if (distance[femalePosition[i]][j] != Integer.MAX_VALUE / 2 && (long) distance[femalePosition[i]][j] * femaleTime[i] <= time) {
							graph.addFlowEdge(2 * malePosition.length + rowCount * columnCount + j, malePosition.length + i, 1);
						}
					}
				}
			}
			lastResult = MaxFlow.dinic(graph, source, sink) + lastResult;
			last = time;
			return  lastResult == malePosition.length;
		}

		private int read(InputReader in) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			return row * columnCount + column;
		}
}
