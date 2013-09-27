package on2013_09.on2013_09_25_Codeforces_Trainings_Season_1_Episode_3.TaskL;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskL {
	long[] count = new long[60];
	long[][] c = IntegerUtils.generateBinomialCoefficients(60);

	NumberIterator iterator = new NumberIterator(2) {
		@Override
		protected void process(long prefix, int remainingDigits) {
			int shift = Long.bitCount(prefix);
			for (int i = 0; i <= remainingDigits; i++)
				count[i + shift] += c[remainingDigits][i];
		}
	};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		int length = in.readInt();
		if (from == 0 && to == 0)
			throw new UnknownError();
		if (length == 0) {
			out.printLine(from == 1 ? 1 : 0);
			return;
		}
		if (to == 1) {
			out.printLine(0);
			return;
		}
		Arrays.fill(count, 0);
		iterator.run(Math.max(from, 2), to);
		long answer = 0;
		for (int i = 1; i < 60; i++) {
			int curLength = 1;
			int current = i;
			while (current != 1) {
				current = Integer.bitCount(current);
				curLength++;
			}
			if (curLength == length)
				answer += count[i];
		}
		out.printLine(answer);
    }
}
