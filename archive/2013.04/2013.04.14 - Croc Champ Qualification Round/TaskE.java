package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = new int[count - 1];
		String[] s = new String[count - 1];
		for (int i = 0; i < count - 1; i++) {
			parent[i] = in.readInt() - 1;
			s[i] = in.readString();
		}
		String target = in.readString();
		int[][] automaton = StringUtils.buildPrefixAutomaton(target);
		int[] vertex = new int[count - 1];
		for (int i = 0; i < count - 1; i++)
			vertex[i] = i + 1;
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, parent, vertex);
		int[] state = new int[count];
		int[] queue = new int[count];
		int size = 1;
		int answer = 0;
		for (int i = 0; i < size; i++) {
			int root = queue[i];
			for (int j : graph[root]) {
				int current = state[root];
				for (int k = 0; k < s[j - 1].length(); k++) {
					current = automaton[current][s[j - 1].charAt(k) - 'a'];
					if (current == target.length())
						answer++;
				}
				state[j] = current;
				queue[size++] = j;
			}
		}
		out.printLine(answer);
    }
}
