package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LittleBearAndStrings {
	private long[] result;
	private boolean[] starts;
	private SuffixAutomaton automaton;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		String sample = in.readString();
		String start = in.readString();
		String end = in.readString();
		automaton = new SuffixAutomaton(sample);
		starts = new boolean[automaton.size];
		int[] queue = new int[automaton.size];
		int size = 0;
		int endPosition = 0;
		int[] zz = StringUtils.zAlgorithm(end + sample);
		for (int i = 0; i < sample.length(); i++) {
			if (zz[i + end.length()] >= end.length()) {
				starts[endPosition] = true;
				queue[size++] = endPosition;
			}
			int edge = automaton.findEdge(endPosition, sample.charAt(i));
			endPosition = automaton.to[edge];
		}
		for (int i = 0; i < size; i++) {
			int next = automaton.link[queue[i]];
			if (next != -1 && !starts[next]) {
				starts[next] = true;
				queue[size++] = next;
			}
		}
		int[] z = StringUtils.zAlgorithm(end + start);
		int startPosition = 0;
		long answer = 0;
		for (int i = 0; i < start.length(); i++) {
			if (starts[startPosition] && z[end.length() + i] == start.length() - i)
				answer++;
			char c = start.charAt(i);
			int edge = automaton.findEdge(startPosition, c);
			if (edge == -1) {
				out.printLine(0);
				return;
			}
			startPosition = automaton.to[edge];
		}
		Graph graph = new Graph(automaton.size, automaton.edgeSize);
		for (int i = 0; i < automaton.size; i++) {
			for (int j = automaton.first[i]; j != -1; j = automaton.next[j])
				graph.addSimpleEdge(i, automaton.to[j]);
		}
		int[] order = GraphAlgorithms.topologicalSort(graph);
		ArrayUtils.reverse(order);
		result = new long[automaton.size];
		Arrays.fill(result, -1);
		for (int i : order)
			calculate(i);
		answer += calculate(startPosition);
		out.printLine(answer);
    }

	private long calculate(int position) {
		if (result[position] != -1)
			return result[position];
		result[position] = starts[position] ? 1 : 0;
		for (int i = automaton.first[position]; i != -1; i = automaton.next[i])
			result[position] += calculate(automaton.to[i]);
		return result[position];
	}
}
