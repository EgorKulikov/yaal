package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ReverseShuffleMerge {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		ArrayUtils.reverse(s);
		int[] next = new int[26];
		int[] haveTo = new int[26];
		int[] qty = new int[26];
		for (int i = 0; i < s.length; i++)
			qty[s[i] - 'a']++;
		int[] remaining = new int[26];
		for (int i = 0; i < 26; i++)
			remaining[i] = qty[i] /= 2;
		Arrays.fill(haveTo, s.length);
		for (int i = 0; i < 26; i++) {
			if (qty[i] == 0)
				continue;
			for (int j = s.length - 1; j >= 0; j--) {
				if (s[j] == i + 'a')
					qty[i]--;
				if (qty[i] == 0) {
					haveTo[i] = j;
					break;
				}
			}
		}
		Arrays.fill(next, s.length);
		for (int i = s.length - 1; i >= 0; i--)
			next[s[i] - 'a'] = i;
		char[] answer = new char[s.length / 2];
		int position = 0;
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < 26; j++) {
				if (remaining[j] == 0)
					continue;
				boolean good = true;
				for (int k = j + 1; k < 26; k++) {
					if (haveTo[k] < next[j]) {
						good = false;
						break;
					}
				}
				if (good) {
					answer[i] = (char) ('a' + j);
					remaining[j]--;
					position = next[j] + 1;
					if (remaining[j] == 0)
						haveTo[j] = s.length;
					else {
						for (int k = haveTo[j] + 1; k < s.length; k++) {
							if (s[k] == 'a' + j) {
								haveTo[j] = k;
								break;
							}
						}
					}
					for (int k = 0; k < 26; k++) {
						if (next[k] < position) {
							next[k] = s.length;
							for (int l = position; l < s.length; l++) {
								if (s[l] == 'a' + k) {
									next[k] = l;
									break;
								}
							}
						}
					}
					break;
				}
			}
		}
		out.printLine(answer);
    }
}
