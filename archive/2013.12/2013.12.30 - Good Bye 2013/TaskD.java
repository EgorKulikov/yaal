package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int upTo = in.readInt();
		int required = in.readInt();
		int firstLength = in.readInt();
		int secondLength = in.readInt();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int remainingFirstLength = firstLength - i - j;
				if (remainingFirstLength < 0)
					continue;
				for (int k = 0; k * 2 <= remainingFirstLength; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							int remainingSecondLength = secondLength - l - m;
							if (remainingSecondLength < 0)
								continue;
							for (int n = 0; n * 2 <= remainingSecondLength; n++) {
								int lastStartsC = i;
								int lastEndsA = j;
								long last = k;
								int currentStartsC = l;
								int currentEndsA = m;
								long current = n;
								for (int o = 2; o < upTo; o++) {
									int nextStartsC = lastStartsC;
									int nextEndsA = currentEndsA;
									long next = last + current + lastEndsA * currentStartsC;
									lastStartsC = currentStartsC;
									lastEndsA = currentEndsA;
									last = current;
									currentStartsC = nextStartsC;
									currentEndsA = nextEndsA;
									current = next;
								}
								if (current == required) {
									output(out, i, j, remainingFirstLength, k);
									output(out, l, m, remainingSecondLength, n);
									return;
								}
							}
						}
					}
				}
			}
		}
		out.printLine("Happy new year!");
    }

	private void output(OutputWriter out, int i, int j, int remainingFirstLength, int k) {
		StringBuilder first = new StringBuilder();
		if (i != 0)
			first.append('C');
		for (int o = 0; o < k; o++)
			first.append("AC");
		for (int o = 2 * k; o < remainingFirstLength; o++)
			first.append('X');
		if (j != 0)
			first.append('A');
		out.printLine(first);
	}
}
