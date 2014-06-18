package net.egork;

import net.egork.string.SubstringAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TwoTwo {
	String[] twos = new String[801];
	SubstringAutomaton automaton;

	{
		BigInteger current = BigInteger.ONE;
		for (int i = 0; i <= 800; i++) {
			twos[i] = current.toString();
			current = current.shiftLeft(1);
		}
		automaton = new SubstringAutomaton(twos, '0', '9');
	}

	long tAutomaton;
	long tSizes;
	long tCheck;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
/*		tAutomaton -= System.currentTimeMillis();
		String number = in.readString();
		final SuffixAutomaton automaton = new SuffixAutomaton(number);
		tAutomaton += System.currentTimeMillis();
		tSizes -= System.currentTimeMillis();
		int[] qty = new int[automaton.size];
		int state = 0;
		for (int i = 0; i < number.length(); i++) {
			state = automaton.to[automaton.findEdge(state, number.charAt(i))];
			qty[state] = 1;
		}
//		int[] order = ArrayUtils.createOrder(automaton.size);
//		ArrayUtils.sort(order, new IntComparator() {
//			@Override
//			public int compare(int first, int second) {
//				return automaton.length[second] - automaton.length[first];
//			}
//		});
		int[] order = new int[automaton.size];
		int[] degree = new int[automaton.size];
		for (int i = 1; i < automaton.size; i++)
			degree[automaton.link[i]]++;
		int size = 0;
		for (int i = 0; i < automaton.size; i++) {
			if (degree[i] == 0)
				order[size++] = i;
		}
		for (int i = 0; i < size; i++) {
			int current = order[i];
			if (current != 0) {
				int link = automaton.link[current];
				qty[link] += qty[current];
				if (--degree[link] == 0)
					order[size++] = link;
			}
		}
		tSizes += System.currentTimeMillis();
		tCheck -= System.currentTimeMillis();
		long answer = 0;
		for (String s : twos) {
			state = 0;
			boolean toEnd = true;
			for (int i = 0; i < s.length(); i++) {
				int edge = automaton.findEdge(state, s.charAt(i));
				if (edge == -1) {
					toEnd = false;
					break;
				}
				state = automaton.to[edge];
			}
			if (toEnd)
				answer += qty[state];
		}
		out.printLine(answer);
		tCheck += System.currentTimeMillis();
//		if (testNumber == 100)
//			System.err.println(tAutomaton + " " + tSizes + " " + tCheck);
*/
		String number = in.readString();
		long answer = 0;
		int state = 0;
		for (int i = 0; i < number.length(); i++) {
			state = automaton.edges[state][number.charAt(i) - '0'];
			answer += automaton.ends[state];
		}
		out.printLine(answer);
	}
}
