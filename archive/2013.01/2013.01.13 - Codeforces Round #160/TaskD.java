package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testCount = in.readInt();
		int count = in.readInt();
		int max = in.readInt();
		int repeats = in.readInt();
		final int[] numbers = new int[count];
		int[] copy = new int[count];
		int[] place = new int[max];
		int[] qty = new int[Math.min(max, count)];
		int[] from = new int[Math.min(max, count)];
		int[] to = new int[Math.min(max, count)];
		int[] order = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = i;
		int[] answer = new int[Math.min(max, count)];
		int[] shift = new int[Math.min(max, count)];
		for (int i = 0; i < testCount; i++) {
			for (int j = 0; j < count; j++)
				numbers[j] = in.readInt() - 1;
			System.arraycopy(numbers, 0, copy, 0, count);
			ArrayUtils.sort(copy, IntComparator.DEFAULT);
			int k = 1;
			for (int j = 1; j < count; j++) {
				if (copy[j] != copy[j - 1])
					copy[k++] = copy[j];
			}
			for (int j = 0; j < k; j++)
				place[copy[j]] = j;
			Arrays.fill(qty, 0);
			for (int j = 0; j < count; j++)
				qty[place[numbers[j]]]++;
			int current = 0;
			for (int j = 0; j < k; j++) {
				from[j] = current;
				current += qty[j];
				to[j] = current;
			}
			ArrayUtils.sort(order, new IntComparator() {
				public int compare(int first, int second) {
					if (numbers[first] != numbers[second])
						return numbers[first] - numbers[second];
					return first - second;
				}
			});
			answer[0] = order[0];
			shift[0] = 0;
			for (int j = 1; j < k; j++) {
				shift[j] = Integer.MAX_VALUE;
				for (int l = j - 1; l >= 0; l--) {
					int candidateAnswer = -Arrays.binarySearch(order, from[j], to[j], answer[l]) - 1;
					int candidateShift = shift[l];
					if (candidateAnswer == to[j]) {
						candidateAnswer = from[j];
						candidateShift++;
					}
					candidateAnswer = order[candidateAnswer];
					if (shift[l + 1] > candidateShift || shift[l + 1] == candidateShift && answer[l + 1] > candidateAnswer) {
						answer[l + 1] = candidateAnswer;
						shift[l + 1] = candidateShift;
					}
				}
				answer[0] = Math.min(answer[0], order[from[j]]);
			}
			int result = -1;
			for (int j = 0; j < k; j++) {
				if (shift[j] < repeats)
					result = j + 1;
			}
			out.printLine(result);
		}
    }
}
