package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int size = s.length;
		boolean[] processed = new boolean[size];
		for (int i = 1; i * 2 <= size; i++) {
			int j = 0;
			int k = i;
			int same = 0;
			boolean change = false;
			while (k < size) {
				if (s[j] == s[k])
					same++;
				else
					same = 0;
				if (same == i) {
					for (int l = 0; l < i; l++) {
						processed[j - l] = true;
					}
					same = 0;
					change = true;
				}
				j++;
				k++;
			}
			if (change) {
				int newSize = 0;
				for (int l = 0; l < size; l++) {
					if (!processed[l])
						s[newSize++] = s[l];
				}
				size = newSize;
				Arrays.fill(processed, 0, size, false);
			}
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; i++)
			builder.append(s[i]);
		out.printLine(builder);
    }
}
