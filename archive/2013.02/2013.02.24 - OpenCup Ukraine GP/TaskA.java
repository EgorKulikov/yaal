package net.egork;

import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		SuffixAutomaton automaton = new SuffixAutomaton(s);
/*		int[] index = new int[automaton.size];
		Arrays.fill(index, -1);
		int current = 0;
		for (int i = 0; i < s.length(); i++) {
			int j = current;
			while (j != -1 && index[j] == -1) {
				index[j] = i;
				j = automaton.link[j];
			}
			current = automaton.to[automaton.findEdge(current, s.charAt(i))];
		}
		index[current] = s.length();
		int[][] next = new int[s.length()][26];
		ArrayUtils.fill(next, -1);
		for (int i = 0; i < automaton.size; i++) {
			int id = automaton.first[i];
			while (id != -1) {
				next[index[i]][automaton.label[id] - 'a'] = index[automaton.to[id]] + 1;
				id = automaton.next[id];
			}
		}
		int edgeCount = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (next[i][j] != -1)
					edgeCount++;
			}
		}
		out.printLine(s.length() + 1, edgeCount);
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (next[i][j] != -1)
					out.printLine(i + 1, next[i][j], (char)('a' + j));
			}
		}*/
		out.printLine(automaton.size, automaton.edgeSize);
		for (int i = 0; i < automaton.size; i++) {
			int id = automaton.first[i];
			while (id != -1) {
				out.printLine(i + 1, automaton.to[id] + 1, (char)automaton.label[id]);
				id = automaton.next[id];
			}
		}
    }
}
