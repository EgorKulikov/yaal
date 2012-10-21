package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] languageMask = new long[count];
		for (int i = 0; i < count; i++) {
			int current = in.readInt();
			for (int j = 0; j < current; j++)
				languageMask[i] |= 1L << in.readInt();
		}
		long currentMask = languageMask[0];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if ((currentMask & languageMask[j]) != 0)
					currentMask |= languageMask[j];
			}
		}
		int answer = 0;
		for (int j = 0; j < count; j++) {
			if ((currentMask & languageMask[j]) != 0)
				answer++;
		}
		out.printLine(Math.max(answer, 1));
	}
}
