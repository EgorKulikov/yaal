package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		String[] smaller = new String[count];
		long answer = 0;
		for (int i = 0; i < count; i++) {
			smaller[i] = words[i];
			for (int j = 0; j < words[i].length(); j++) {
				if (words[i].charAt(j) != 'a') {
					smaller[i] = words[i].substring(0, j) + 'a' + words[i].substring(j + 1);
					answer--;
					break;
				}
			}
		}
		Arrays.sort(words);
		Arrays.sort(smaller);
		int at = 0;
		for (String word : words) {
			while (at < count && smaller[at].compareTo(word) < 0) {
				at++;
			}
			answer += at;
		}
		out.printLine(answer);
	}
}
