package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ColoringBlocks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		char[] colors = in.readString().toCharArray();
		boolean full = false;
		int answer = 0;
		int current = 1;
		for (int i = 1; i < count; i++) {
			if (colors[i] != colors[i - 1] || current == size) {
				answer++;
				if (current == size)
					full = true;
				current = 0;
			}
			current++;
		}
		if (current == size)
			full = true;
		answer++;
		if (full)
			out.printLine(answer);
		else
			out.printLine(-1);
    }
}
