package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] v = new int[n];
		int[] d = new int[n];
		int[] p = new int[n];
		IOUtils.readIntArrays(in, v, d, p);
		boolean[] inQueue = new boolean[n];
		Arrays.fill(inQueue, true);
		IntList answer = new IntArrayList();
		for (int i = 0; i < n; i++) {
			if (inQueue[i]) {
				answer.add(i + 1);
				IntList runAway = new IntArrayList();
				inQueue[i] = false;
				int current = v[i];
				for (int j = i + 1; j < n && current > 0; j++) {
					if (inQueue[j]) {
						p[j] -= current--;
						if (p[j] < 0) {
							inQueue[j] = false;
							runAway.add(j);
						}
					}
				}
				for (int l = 0; l < runAway.size(); l++) {
					int j = runAway.get(l);
					for (int k = j + 1; k < n; k++) {
						if (inQueue[k]) {
							p[k] -= d[j];
							if (p[k] < 0) {
								inQueue[k] = false;
								runAway.add(k);
							}
						}
					}
				}
			}
		}
		out.printLine(answer.size());
		out.printLine(answer);
	}
}
