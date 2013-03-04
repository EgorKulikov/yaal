package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] string = in.readString().toCharArray();
		char[] sample = in.readString().toCharArray();
		if (string.length < sample.length) {
			out.printLine(0);
			return;
		}
		int[] sampleCount = new int[256];
		for (char c : sample)
			sampleCount[c]++;
		int[] count = new int[256];
		int bad = 0;
		for (int i = 0; i < sample.length; i++) {
			if (string[i] != '?') {
				count[string[i]]++;
				if (count[string[i]] == sampleCount[string[i]] + 1)
					bad++;
			}
		}
		int answer = 0;
		if (bad == 0)
			answer++;
		for (int i = sample.length; i < string.length; i++) {
			char current = string[i];
			if (current != '?') {
				count[current]++;
				if (count[current] == sampleCount[current] + 1)
					bad++;
			}
			char first = string[i - sample.length];
			if (first != '?') {
				count[first]--;
				if (count[first] == sampleCount[first])
					bad--;
			}
			if (bad == 0)
				answer++;
		}
		out.printLine(answer);
	}
}
