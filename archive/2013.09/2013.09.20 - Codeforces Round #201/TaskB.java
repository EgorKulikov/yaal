package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	char[] first;
	char[] second;
	char[] virus;
	int[][][] length;
	char[][][] next;
	int[][] automaton;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		first = in.readString().toLowerCase().toCharArray();
		second = in.readString().toLowerCase().toCharArray();
		virus = in.readString().toLowerCase().toCharArray();
		automaton = StringUtils.buildPrefixAutomaton(new String(virus));
		length = new int[first.length + 1][second.length + 1][virus.length];
		next = new char[first.length + 1][second.length + 1][virus.length];
		ArrayUtils.fill(length, -1);
		if (go(0, 0, 0) == 0)
			out.printLine(0);
		else {
			StringBuilder answer = new StringBuilder();
			int i = 0;
			int j = 0;
			int k = 0;
			while (length[i][j][k] != 0) {
				char c = next[i][j][k];
				answer.append(c);
				while (first[i] != c)
					i++;
				i++;
				while (second[j] != c)
					j++;
				j++;
				k = automaton[k][c - 'a'];
			}
			out.printLine(answer.toString().toUpperCase());
		}
    }

	private int go(int i, int j, int k) {
		if (length[i][j][k] != -1)
			return length[i][j][k];
		if (i == first.length || j == second.length)
			return length[i][j][k] = 0;
		if (first[i] != second[j]) {
			int left = go(i + 1, j, k);
			int right = go(i, j + 1, k);
			if (left > right) {
				length[i][j][k] = left;
				next[i][j][k] = next[i + 1][j][k];
			} else {
				length[i][j][k] = right;
				next[i][j][k] = next[i][j + 1][k];
			}
			return length[i][j][k];
		}
		length[i][j][k] = go(i + 1, j + 1, k);
		next[i][j][k] = next[i + 1][j + 1][k];
		if (automaton[k][first[i] - 'a'] != virus.length) {
			int candidate = go(i + 1, j + 1, automaton[k][first[i] - 'a']) + 1;
			if (candidate > length[i][j][k]) {
				length[i][j][k] = candidate;
				next[i][j][k] = first[i];
			}
		}
		return length[i][j][k];
	}
}
