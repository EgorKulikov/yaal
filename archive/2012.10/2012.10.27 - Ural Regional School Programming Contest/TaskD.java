package net.egork;

import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int joinCount = in.readInt();
		int[] order = IOUtils.readIntArray(in, joinCount);
		int[] fullOrder = new int[joinCount + 2];
		System.arraycopy(order, 0, fullOrder, 2, joinCount);
		fullOrder[0] = map[rowCount - 1][0] - '0';
		fullOrder[1] = map[0][columnCount - 1] - '0';
		List<Pair<Integer, Integer>> first = new ArrayList<Pair<Integer, Integer>>();
		List<Pair<Integer, Integer>> second = new ArrayList<Pair<Integer, Integer>>();
		boolean[][] occupied = new boolean[rowCount][columnCount];
		occupied[rowCount - 1][0] = true;
		occupied[0][columnCount - 1] = true;
		first.add(Pair.makePair(rowCount - 1, 0));
		second.add(Pair.makePair(0, columnCount - 1));
		for (int i : fullOrder) {
			for (int j = 0; j < first.size(); j++) {
				int row = first.get(j).first;
				int column = first.get(j).second;
				for (int k = 0; k < 4; k++) {
					int nextRow = row + MiscUtils.DX4[k];
					int nextColumn = column + MiscUtils.DY4[k];
					if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount &&
						!occupied[nextRow][nextColumn] && map[nextRow][nextColumn] == i + '0')
					{
						occupied[nextRow][nextColumn] = true;
						first.add(Pair.makePair(nextRow, nextColumn));
					}
				}
			}
			List<Pair<Integer, Integer>> temp = first;
			first = second;
			second = temp;
		}
		if (joinCount % 2 == 1) {
			List<Pair<Integer, Integer>> temp = first;
			first = second;
			second = temp;
		}
		out.printLine(first.size(), second.size());
	}
}
