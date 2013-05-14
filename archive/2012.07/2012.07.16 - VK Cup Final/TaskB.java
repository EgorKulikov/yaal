package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] trace = new int[26 * s.length];
		int index = 0;
		boolean[] found = new boolean[26];
		int[] order = new int[26];
		for (int i = 0; i < 26; i++) {
			order[i] = i;
		}
		for (int i = s.length - 1; i >= 0; i--) {
			int mask = 0;
			int current = s[i] - 'a';
			for (int j = 0; j < 26; j++) {
				if (order[j] == current) {
					for (int k = j; k > 0; k--)
						order[k] = order[k - 1];
					order[0] = current;
					break;
				}
				mask += 1 << order[j];
				if (found[order[j]])
					trace[index++] = mask << 1;
			}
			found[current] = true;
		}
		int mask = 0;
		for (int j : order) {
			if (!found[j])
				break;
			mask += 1 << j;
			trace[index++] = 2 * mask;
		}
		Arrays.sort(trace, 0, index);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			char[] good = in.readString().toCharArray();
			mask = 0;
			for (char c : good)
				mask += 1 << (c - 'a');
			int result = Arrays.binarySearch(trace, 0, index, 2 * mask - 1) - Arrays.binarySearch(trace, 0, index, 2 * mask + 1);
			out.printLine(result);
		}
	}
}
