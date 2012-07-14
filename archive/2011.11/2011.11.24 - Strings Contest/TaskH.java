package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskH {
	char[] sample = "the quick brown fox jumps over the lazy dog".toCharArray();

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<char[]> samples = new ArrayList<char[]>();
		while (!in.isExhausted())
			samples.add(in.readLine(false).toCharArray());
		int index = -1;
		char[] map = new char[256];
		for (int i = 0; i < samples.size(); i++) {
			char[] current = samples.get(i);
			if (current.length != sample.length)
				continue;
			boolean good = true;
			for (int j = 0; j < current.length && good; j++) {
				if (current[j] == ' ' && sample[j] != ' ')
					good = false;
				for (int k = j + 1; k < current.length && good; k++) {
					if (current[j] == current[k] ^ sample[j] == sample[k])
						good = false;
				}
				map[current[j]] = sample[j];
			}
			if (good) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			out.printLine("No solution.");
			return;
		}
		for (char[] sample : samples) {
			for (char c : sample)
				out.print(map[c]);
			out.printLine();
		}
	}
}
