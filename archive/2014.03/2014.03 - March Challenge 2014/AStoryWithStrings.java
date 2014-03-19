package net.egork;

import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AStoryWithStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		SuffixAutomaton automaton = new SuffixAutomaton(first);
		int at = 0;
		int length = 0;
		int last = 0;
		String answer = "";
		for (char c : second.toCharArray()) {
			last++;
			while (at != 0 && automaton.findEdge(at, c) == -1) {
				at = automaton.link[at];
				length = automaton.length[at];
			}
			if (automaton.findEdge(at, c) != -1) {
				at = automaton.to[automaton.findEdge(at, c)];
				length++;
			}
			if (length > answer.length())
				answer = second.substring(last - length, last);
		}
		if (!answer.isEmpty())
			out.printLine(answer);
		out.printLine(answer.length());
    }
}
