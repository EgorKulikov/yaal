package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;

public class TopBatsmen {
	long[][] c = IntegerUtils.generateBinomialCoefficients(12);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] scores = IOUtils.readIntArray(in, 11);
		int toSelect = in.readInt();
		Collections.sort(Array.wrap(scores), new ReverseComparator<Integer>());
		int lessSame = 0;
		for (int i = toSelect - 1; i >= 0; i--) {
			if (scores[i] == scores[toSelect - 1])
				lessSame++;
		}
		int moreSame = 0;
		for (int i = toSelect; i < 11; i++) {
			if (scores[i] == scores[toSelect - 1])
				moreSame++;
		}
		out.printLine(c[lessSame + moreSame][lessSame]);
	}
}
