package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		int closingCount = in.readInt();
		for (int i = 0; i < closingCount; i++)
			sequence[in.readInt() - 1] *= -1;
		int[] primaryStack = new int[count];
		int sizePrimary = 0;
		int[] secondaryStack = new int[count];
		int sizeSecondary = 0;
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] > 0)
				primaryStack[sizePrimary++] = i;
			else {
				while (sizePrimary > 0 && !(sequence[primaryStack[sizePrimary - 1]] == -sequence[i] && sizeSecondary == 0)) {
					if (sizeSecondary > 0 && sequence[secondaryStack[sizeSecondary - 1]] == sequence[primaryStack[sizePrimary - 1]]) {
						sequence[secondaryStack[--sizeSecondary]] *= -1;
						sizePrimary--;
					} else
						secondaryStack[sizeSecondary++] = primaryStack[--sizePrimary];
				}
				if (sizePrimary == 0) {
					out.printLine("NO");
					return;
				}
				sizePrimary--;
			}
		}
		while (sizePrimary > 0) {
			if (sizeSecondary > 0 && sequence[secondaryStack[sizeSecondary - 1]] == sequence[primaryStack[sizePrimary - 1]]) {
				sequence[secondaryStack[--sizeSecondary]] *= -1;
				sizePrimary--;
			} else
				secondaryStack[sizeSecondary++] = primaryStack[--sizePrimary];
		}
		if (sizeSecondary != 0) {
			out.printLine("NO");
			return;
		}
		out.printLine("YES");
		out.printLine(sequence);
    }
}
