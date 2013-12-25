package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		final int[] numbers = IOUtils.readIntArray(in, count);
		int canConvert = in.readInt();
		TreapSet<Integer> positive = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = numbers[o2] - numbers[o1];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		TreapSet<Integer> negative = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = numbers[o1] - numbers[o2];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		long positiveSum = 0;
		long negativeSum = 0;
		long topPositiveSum = 0;
		long topNegativeSum = 0;
		for (int i = 0; i < length; i++) {
			if (numbers[i] > 0) {
				positiveSum += numbers[i];
				positive.add(i);
				if (positive.headSet(i, false).size() < canConvert) {
					topPositiveSum += numbers[i];
					if (positive.size() > canConvert)
						topPositiveSum -= numbers[positive.get(canConvert)];
				}
			} else if (numbers[i] < 0) {
				negativeSum -= numbers[i];
				negative.add(i);
				if (negative.headSet(i, false).size() < canConvert) {
					topNegativeSum -= numbers[i];
					if (negative.size() > canConvert)
						topNegativeSum += numbers[negative.get(canConvert)];
				}
			}
		}
		long answer = Math.max(positiveSum - negativeSum + 2 * topNegativeSum, negativeSum - positiveSum + 2 * topPositiveSum);
		for (int i = length; i < count; i++) {
			if (numbers[i - length] > 0) {
				positiveSum -= numbers[i - length];
				if (positive.headSet(i - length, false).size() < canConvert) {
					topPositiveSum -= numbers[i - length];
					if (positive.size() > canConvert)
						topPositiveSum += numbers[positive.get(canConvert)];
				}
				positive.remove(i - length);
			} else if (numbers[i - length] < 0) {
				negativeSum += numbers[i - length];
				if (negative.headSet(i - length, false).size() < canConvert) {
					topNegativeSum += numbers[i - length];
					if (negative.size() > canConvert)
						topNegativeSum -= numbers[negative.get(canConvert)];
				}
				negative.remove(i - length);
			}
			if (numbers[i] > 0) {
				positiveSum += numbers[i];
				positive.add(i);
				if (positive.headSet(i, false).size() < canConvert) {
					topPositiveSum += numbers[i];
					if (positive.size() > canConvert)
						topPositiveSum -= numbers[positive.get(canConvert)];
				}
			} else if (numbers[i] < 0) {
				negativeSum -= numbers[i];
				negative.add(i);
				if (negative.headSet(i, false).size() < canConvert) {
					topNegativeSum -= numbers[i];
					if (negative.size() > canConvert)
						topNegativeSum += numbers[negative.get(canConvert)];
				}
			}
			answer = Math.max(answer, Math.max(positiveSum - negativeSum + 2 * topNegativeSum, negativeSum - positiveSum + 2 * topPositiveSum));
		}
		out.printLine(answer);
	}
}
