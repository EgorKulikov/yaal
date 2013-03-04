package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int waterLevel = in.readInt();
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] ceiling = IOUtils.readIntTable(in, rowCount, columnCount);
		int[][] floor = IOUtils.readIntTable(in, rowCount, columnCount);
		final double[][] answer = new double[rowCount][columnCount];
		ArrayUtils.fill(answer, Double.POSITIVE_INFINITY);
		answer[0][0] = 0;
		Queue<Pair<Integer, Integer>> queue = new ArrayDeque<Pair<Integer, Integer>>();
		queue.add(Pair.makePair(0, 0));
		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int column = queue.poll().second;
			for (int i = 0; i < 4; i++) {
				int newRow = row + MiscUtils.DX4[i];
				int newColumn = column + MiscUtils.DY4[i];
				if (newRow >= 0 && newRow < rowCount && newColumn >= 0 && newColumn < columnCount
					&& answer[newRow][newColumn] != 0 && Math.min(ceiling[newRow][newColumn], ceiling[row][column]) >=
					50 + Math.max(waterLevel, Math.max(floor[newRow][newColumn], floor[row][column])))
				{
					answer[newRow][newColumn] = 0;
					queue.add(Pair.makePair(newRow, newColumn));
				}
			}
		}
		boolean[][] processed = new boolean[rowCount][columnCount];
		queue = new PriorityQueue<Pair<Integer, Integer>>(rowCount * columnCount, new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
				return Double.compare(answer[o1.first][o1.second], answer[o2.first][o2.second]);
			}
		});
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (answer[i][j] == 0)
					queue.add(Pair.makePair(i, j));
			}
		}
		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int column = queue.poll().second;
			if (processed[row][column])
				continue;
			processed[row][column] = true;
			for (int i = 0; i < 4; i++) {
				int newRow = row + MiscUtils.DX4[i];
				int newColumn = column + MiscUtils.DY4[i];
				if (newRow >= 0 && newRow < rowCount && newColumn >= 0 && newColumn < columnCount && answer[newRow][newColumn] != 0) {
					if (Math.min(ceiling[newRow][newColumn], ceiling[row][column]) < 50 + Math.max(floor[newRow][newColumn], floor[row][column]))
						continue;
					double firstTimePassable = (waterLevel - Math.min(ceiling[newRow][newColumn], ceiling[row][column]) + 50) / 10d;
					double curTime = Math.max(firstTimePassable, answer[row][column]);
					double curWaterLevel = waterLevel - 10 * curTime;
					if (curWaterLevel - floor[row][column] > 20 - GeometryUtils.epsilon)
						curTime++;
					else
						curTime += 10;
					if (answer[newRow][newColumn] > curTime) {
						answer[newRow][newColumn] = curTime;
						queue.add(Pair.makePair(newRow, newColumn));
					}
				}
			}
		}
		out.printLine("Case #" + testNumber + ":", answer[rowCount - 1][columnCount - 1]);
	}
}
