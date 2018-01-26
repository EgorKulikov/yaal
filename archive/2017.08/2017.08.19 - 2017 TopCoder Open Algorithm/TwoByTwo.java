package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;

import static net.egork.misc.MiscUtils.DX4;
import static net.egork.misc.MiscUtils.DY4;
import static net.egork.misc.MiscUtils.isValidCell;

public class TwoByTwo {
    public int minimal(String[] board) {
        int n = board.length;
        int m = board[0].length();
        Graph graph = new Graph((n - 1) * (m - 1));
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                for (int k = 0; k < 4; k++) {
                    int ni = i + DX4[k];
                    int nj = j + DY4[k];
                    if (isValidCell(ni, nj, n - 1, m - 1)) {
                        int cost = 0;
                        for (int l = ni; l < ni + 2; l++) {
                            for (int o = nj; o < nj + 2; o++) {
                                if (board[l].charAt(o) == '#' && (l < i || l >= i + 2 || o < j || o >= j + 2)) {
                                    cost++;
                                }
                            }
                        }
                        graph.addWeightedEdge(i * (m - 1) + j, ni * (m - 1) + nj, cost);
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (board[i].charAt(j) == '#') {
                    answer++;
                }
            }
        }
        answer += ShortestDistance.dijkstraAlgorithm(graph, 0, graph.vertexCount() - 1).first;
        return answer;
    }
}
