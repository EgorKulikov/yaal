package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.collections.set.EHashSet;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

import java.util.*;

public class Balance {
    public String canTransform(String[] initial, String[] target) {
        String encInit = encode(initial);
        String encTarg = encode(target);
		return encInit.equals(encTarg) ? "Possible" : "Impossible";
    }

    private String encode(String[] table) {
        char[][] map = new char[table.length + 2][table[0].length() + 2];
        ArrayUtils.fill(map, '#');
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length(); j++) {
                map[i + 1][j + 1] = table[i].charAt(j);
            }
        }
        int[][] id = new int[map.length][map[0].length];
        ArrayUtils.fill(id, -1);
        int next = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (id[i][j] != -1) {
                    continue;
                }
                Queue<IntPair> queue = new ArrayDeque<>();
                queue.add(new IntPair(i, j));
                id[i][j] = next;
                while (!queue.isEmpty()) {
                    int row = queue.peek().first;
                    int column = queue.poll().second;
                    for (int l = 0; l < 4; l++) {
                        int nRow = row + MiscUtils.DX4[l];
                        int nColumn = column + MiscUtils.DY4[l];
                        if (MiscUtils.isValidCell(nRow, nColumn, map.length, map[0].length) && map[nRow][nColumn] == map[row][column] && id[nRow][nColumn] == -1) {
                            id[nRow][nColumn] = next;
                            queue.add(new IntPair(nRow, nColumn));
                        }
                    }
                }
                next++;
            }
        }
        Set<IntPair> edges = new EHashSet<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i + 1 < map.length && id[i][j] != id[i + 1][j]) {
                    edges.add(new IntPair(id[i][j], id[i + 1][j]));
                    edges.add(new IntPair(id[i + 1][j], id[i][j]));
                }
                if (j + 1 < map[i].length && id[i][j] != id[i][j + 1]) {
                    edges.add(new IntPair(id[i][j], id[i][j + 1]));
                    edges.add(new IntPair(id[i][j + 1], id[i][j]));
                }
            }
        }
        Graph graph = new Graph(next, edges.size());
        for (IntPair edge : edges) {
            graph.addSimpleEdge(edge.first, edge.second);
        }
        return build(0, -1, graph);
    }

    private String build(int vertex, int last, Graph graph) {
        List<String> subTrees = new ArrayList<>();
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            if (graph.destination(i) != last) {
                subTrees.add(build(graph.destination(i), vertex, graph));
            }
        }
        Collections.sort(subTrees);
        StringBuilder result = new StringBuilder();
        result.append('(');
        for (String s : subTrees) {
            result.append(s);
        }
        result.append(')');
        return result.toString();
    }
}
