package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CielAndQuizGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int select = in.readInt();
		int[] percentages = IOUtils.readIntArray(in, total);
		int[] answer = null;
		double probability = -1;
		Integer[] order = ListUtils.order(Array.wrap(percentages));
		int firstNonZero = 0;
		while (firstNonZero < total && percentages[order[firstNonZero]] == 0)
			firstNonZero++;
		if (firstNonZero >= total - select + 2) {
			out.printLine(Array.wrap(ArrayUtils.range(1, select)).toArray());
			return;
		}
		if (firstNonZero != 0) {
			answer = new int[select];
			answer[0] = order[0];
			probability = 1;
			for (int i = 1; i < select; i++) {
				answer[i] = order[total - i];
				probability *= percentages[order[total - i]] / 100.;
			}
		}
		if (firstNonZero <= total - select) {
			for (int i = 0; i <= select; i++) {
				int[] selected = new int[select];
				for (int j = 0; j < i; j++)
					selected[j] = order[firstNonZero + j];
				for (int j = total - select + i; j < total; j++)
					selected[j - total + select] = order[j];
				double current = 0;
				double p = 1;
				for (int j : selected)
					p *= percentages[j] / 100.;
				for (int j : selected)
					current += (100. - percentages[j]) / percentages[j];
				current *= p;
				if (current > probability) {
					probability = current;
					answer = selected;
				}
			}
		}
		Arrays.sort(answer);
		ListUtils.increment(Array.wrap(answer));
		out.printLine(Array.wrap(answer).toArray());
	}
}
