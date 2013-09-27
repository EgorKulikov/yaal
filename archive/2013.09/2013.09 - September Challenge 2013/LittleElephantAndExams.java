package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndExams {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] probability = new int[count];
		int[] from = new int[count];
		int[] to = new int[count];
		IOUtils.readIntArrays(in, probability, from, to);
		if (count > 16) {
			out.printLine(0);
			return;
		}
		double answer = 0;
		for (int i = 0; i < (1 << count); i++) {
			int mask = 0;
			double current = 1;
			for (int j = 0; j < count; j++) {
				int curNumber;
				double curProbability;
				if ((i >> j & 1) == 0) {
					curNumber = from[j];
					curProbability = probability[j] / 100d;
				} else {
					curNumber = to[j];
					curProbability = 1 - probability[j] / 100d;
				}
				if ((mask >> curNumber & 1) == 1) {
					current = 0;
					break;
				}
				current *= curProbability;
				mask += 1 << curNumber;
			}
			answer += current;
		}
		out.printLine(answer);
    }
}
