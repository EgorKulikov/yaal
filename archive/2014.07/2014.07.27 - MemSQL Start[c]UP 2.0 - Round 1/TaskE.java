package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.string.SubstringAutomaton;
import net.egork.string.SuffixAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = (long) 1e9 + 7;
	SuffixAutomaton automaton;
	private long[][] ways;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s1 = in.readString();
		String s2 = in.readString();
		String s3 = in.readString();
		String all = s1 + "0" + s2 + "1" + s3 + "2";
		automaton = new SuffixAutomaton(all);
		ways = new long[3][automaton.size];
		ArrayUtils.fill(ways, -1);
		int size = Math.min(s1.length(), Math.min(s2.length(), s3.length()));
		IntervalTree tree = new SumIntervalTree(size + 1);
		for (int i = 1; i < automaton.size; i++) {
			calculate(i);
			tree.update(automaton.length[automaton.link[i]] + 1, automaton.length[i], ways[0][i] * ways[1][i] * ways[2][i]);
		}
		long[] answer = new long[size];
		for (int i = 0; i < size; i++) {
			answer[i] = tree.query(i + 1, i + 1) % MOD;
		}
		out.printLine(answer);
	}

	private void calculate(int at) {
		if (ways[0][at] != -1) {
			return;
		}
		ways[0][at] = ways[1][at] = ways[2][at] = 0;
		for (int j = automaton.first[at]; j != -1; j = automaton.next[j]) {
			if (Character.isDigit(automaton.label[j])) {
				ways[automaton.label[j] - '0'][at]++;
			} else {
				calculate(automaton.to[j]);
				for (int i = 0; i < 3; i++) {
					ways[i][at] += ways[i][automaton.to[j]];
				}
			}
		}
	}
}
