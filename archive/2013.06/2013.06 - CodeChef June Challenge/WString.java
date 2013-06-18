package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class WString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] fromStart = new int[s.length];
		int[] qty = new int[256];
		int max = 0;
		for (int i = 0; i < s.length; i++) {
			fromStart[i] = max;
			if (s[i] != '#')
				max = Math.max(max, ++qty[s[i]]);
		}
		int[] fromEnd = new int[s.length];
		Arrays.fill(qty, 0);
		max = 0;
		for (int i = s.length - 1; i >= 0; i--) {
			fromEnd[i] = max;
			if (s[i] != '#')
				max = Math.max(max, ++qty[s[i]]);
		}
		int[] mark = new int[256];
		IntList delta = new IntArrayList();
		max = 0;
		int curMark = 1;
		IntList positions = new IntArrayList();
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '#') {
				delta.add(max);
				max = 0;
				curMark++;
				positions.add(i);
			} else {
				if (mark[s[i]] != curMark) {
					mark[s[i]] = curMark;
					qty[s[i]] = 0;
				}
				max = Math.max(max, ++qty[s[i]]);
			}
		}
		int answer = 0;
		for (int i = 0; i + 2 < delta.size(); i++) {
			if (fromStart[positions.get(i)] == 0 || delta.get(i + 1) == 0 || delta.get(i + 2) == 0 || fromEnd[positions.get(i + 2)] == 0)
				continue;
			answer = Math.max(answer, fromStart[positions.get(i)] + delta.get(i + 1) + delta.get(i + 2) + fromEnd[positions.get(i + 2)] + 3);
		}
		out.printLine(answer);
    }
}
