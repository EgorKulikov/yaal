package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AtticCrossing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] passage = in.readString().toCharArray();
		int last = 0;
		int answer = 0;
		int max = 1;
		for (int i = 1; i < passage.length; i++) {
			if (passage[i] == '#') {
				if (i - last > max) {
					max = i - last;
					answer++;
				}
				last = i;
			}
		}
		out.printLine(answer);
    }
}
