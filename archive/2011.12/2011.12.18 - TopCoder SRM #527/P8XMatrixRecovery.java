package net.egork;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;

public class P8XMatrixRecovery {
	public String[] solve(String[] rows, String[] columns) {
		boolean[][] matching = new boolean[columns.length][columns.length];
		for (int i = 0; i < columns.length; i++) {
			for (int j = 0; j < columns.length; j++) {
				boolean good = true;
				for (int k = 0; k < rows.length && good; k++) {
					if (rows[k].charAt(i) != columns[j].charAt(k) && rows[k].charAt(i) != '?' && columns[j].charAt(k) != '?')
						good = false;
				}
				matching[i][j] = good;
			}
		}
		String[] answer = new String[rows.length];
		for (int i = 0; i < rows.length; i++) {
			StringBuilder row = new StringBuilder(columns.length);
			for (int j = 0; j < columns.length; j++) {
				if (rows[i].charAt(j) != '?')
					row.append(rows[i].charAt(j));
				else {
					Graph graph = new Graph(2 * columns.length + 2);
					int source = 2 * columns.length;
					int sink = source + 1;
					for (int k = 0; k < columns.length; k++) {
						graph.add(new FlowEdge(source, k, 1));
						graph.add(new FlowEdge(columns.length + k, sink, 1));
						for (int l = 0; l < columns.length; l++) {
							if (matching[k][l] && (k != j || columns[l].charAt(i) != '1'))
								graph.add(new FlowEdge(k, columns.length + l, 1));
						}
					}
					char next = GraphAlgorithms.dinic(graph, source, sink) == columns.length ? '0' : '1';
					char other = (char) ('1' + '0' - next);
					for (int k = 0; k < columns.length; k++)
						matching[j][k] &= columns[k].charAt(i) != other;
					row.append(next);
				}
			}
			answer[i] = row.toString();
		}
		return answer;
	}


}

