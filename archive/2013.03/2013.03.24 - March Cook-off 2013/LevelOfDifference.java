package net.egork;

import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LevelOfDifference {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		long answer = calculate(first, second) + calculate(second, first);
		out.printLine(answer);
    }

	private long calculate(String first, String second) {
		SuffixAutomaton automaton = new SuffixAutomaton(first + "$" + second + "#");
		int[] type = new int[automaton.size];
		int[] stack = new int[automaton.size];
		Arrays.fill(type, -1);
		int size = 1;
		int[] curEdge = new int[automaton.size];
		while (size != 0) {
			int current = stack[size - 1];
			if (type[current] == -1) {
				type[current] = 0;
				curEdge[current] = automaton.first[current];
			} else if (curEdge[current] != -1) {
				if (Character.isLetter(automaton.label[curEdge[current]]))
					type[current] |= type[automaton.to[curEdge[current]]];
				curEdge[current] = automaton.next[curEdge[current]];
			}
			if (curEdge[current] == -1) {
				size--;
				continue;
			}
			if (automaton.label[curEdge[current]] == '$')
				type[current] |= 1;
			else if (automaton.label[curEdge[current]] == '#')
				type[current] |= 2;
			else
				stack[size++] = automaton.to[curEdge[current]];
		}
		long answer = 0;
		for (int i = 1; i < automaton.size; i++) {
			if (type[i] == 1)
				answer += automaton.length[i] - automaton.length[automaton.link[i]];
		}
		return answer;
	}
}
