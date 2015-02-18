package on2014_10.on2014_10_23_Single_Round_Match_637.ConnectingGame;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

import java.util.Arrays;

public class ConnectingGame {
    public String isValid(String[] board) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		char[] test = new char[4];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						test[2 * k + l] = board[i - k].charAt(j - l);
					}
				}
				Arrays.sort(test);
				boolean good = true;
				for (int k = 1; k < 4; k++) {
					if (test[k] == test[k - 1]) {
						good = false;
					}
				}
				if (!good) {
					continue;
				}
				Graph graph = new Graph(518);
				for (int k = 0; k < rowCount; k++) {
					for (int l = 0; l < columnCount; l++) {
						for (int m = 0; m < 8; m++) {
							int row = k + MiscUtils.DX8[m];
							int column = l + MiscUtils.DY8[m];
							if (MiscUtils.isValidCell(row, column, rowCount, columnCount)) {
								graph.addFlowEdge(board[k].charAt(l) + 256, board[row].charAt(column), 1);
							}
						}
					}
				}
				for (int k = 0; k < rowCount; k++) {
					graph.addFlowEdge(board[k].charAt(0) + 256, 512, 1);
					graph.addFlowEdge(board[k].charAt(columnCount - 1) + 256, 513, 1);
				}
				for (int k = 0; k < columnCount; k++) {
					graph.addFlowEdge(board[0].charAt(k) + 256, 514, 1);
					graph.addFlowEdge(board[rowCount - 1].charAt(k) + 256, 515, 1);
				}
				for (int k = 512; k < 516; k++) {
					graph.addFlowEdge(k, 517, 1);
				}
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						graph.addFlowEdge(516, board[i - k].charAt(j - l), 1);
					}
				}
				for (int k = 0; k < 256; k++) {
					graph.addFlowEdge(k, k + 256, 1);
				}
				if (MaxFlow.dinic(graph, 516, 517) == 4) {
					return "invalid";
				}
			}
		}
		return "valid";
    }
}
