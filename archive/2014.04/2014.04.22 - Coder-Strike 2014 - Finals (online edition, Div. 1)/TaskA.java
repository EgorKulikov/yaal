package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		boolean[] reverse = new boolean[256];
		reverse['A'] = true;
		reverse['H'] = true;
		reverse['I'] = true;
		reverse['M'] = true;
		reverse['O'] = true;
		reverse['T'] = true;
		reverse['U'] = true;
		reverse['V'] = true;
		reverse['W'] = true;
		reverse['X'] = true;
		reverse['Y'] = true;
		char[] name = in.readString().toCharArray();
		int length = name.length;
		for (int i = 0, j = length - 1; i <= j; i++, j--) {
			if (!reverse[name[i]] || name[i] != name[j]) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
    }
}
