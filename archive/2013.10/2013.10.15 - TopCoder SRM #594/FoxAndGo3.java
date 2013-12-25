package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.MiscUtils;

public class FoxAndGo3 {
    public int maxEmptyCells(String[] board) {
		int size = board.length;
		char[][] cells = new char[size][];
		for (int i = 0; i < size; i++)
			cells[i] = board[i].toCharArray();
		Graph graph = new Graph(size * size + 2);
		int oCount = 0;
		int answer = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j] == 'o') {
					oCount++;
					graph.addFlowEdge(size * size, i + j * size, 1);
					for (int k = 0; k < 4; k++) {
						int ni = i + MiscUtils.DX4[k];
						int nj = j + MiscUtils.DY4[k];
						if (MiscUtils.isValidCell(ni, nj, size, size) && cells[ni][nj] == '.')
							graph.addFlowEdge(i + j * size, ni + nj * size, 1);
					}
				} else if (cells[i][j] == '.') {
					graph.addFlowEdge(i + j * size, size * size + 1, 1);
					answer++;
				}
			}
		}
		answer += oCount - MaxFlow.dinic(graph, size * size, size * size + 1);
		return answer;
    }
}
