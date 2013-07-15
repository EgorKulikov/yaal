package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] silver = new int[1000000];
		int[] gold = new int[1000000];
		int[] ends = new int[1000000];
		for (int i = 0; i < count; i++) {
			char[] current = in.readString().toCharArray();
			for (int j = 0; j < current.length; j++) {
				if (current[j] == 'S')
					silver[j]++;
				else
					gold[j]++;
			}
			ends[current.length - 1]++;
		}
		int answer = 0;
		int curGold = Integer.MAX_VALUE;
		int curSilver = Integer.MAX_VALUE;
		for (int i = 0; i < 1000000; i++) {
			curGold = Math.min(curGold, gold[i]);
			curSilver = Math.min(curSilver, silver[i]);
			gold[i] = curGold;
			silver[i] = curSilver;
		}
		for (int i = 999999; i >= 0; i--)
			answer = Math.min(answer + ends[i], gold[i] + silver[i]);
		out.printLine(answer);
    }
}
