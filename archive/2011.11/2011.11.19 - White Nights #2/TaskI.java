package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(numbers);
		int delta = 10;
		int countSide = 0;
		int countDown = 0;
		int first = -1;
		int second = -1;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int curDelta = Math.abs(getCoupe(numbers[i]) - getCoupe(numbers[j]));
				int curSide = 0;
				if (numbers[i] >= 36)
					curSide++;
				if (numbers[j] >= 36)
					curSide++;
				int curDown = 0;
				if (numbers[i] % 2 == 0)
					curDown++;
				if (numbers[j] % 2 == 0)
					curDown++;
				if (delta > curDelta || delta == curDelta && (countSide > curSide || countSide == curSide && curDown > countDown)) {
					first = numbers[i] + 1;
					second = numbers[j] + 1;
					delta = curDelta;
					countSide = curSide;
					countDown = curDown;
				}
			}
		}
		out.printLine(first, second);
	}

	private int getCoupe(int number) {
		if (number < 36)
			return number / 4;
		return (53 - number) / 2;
	}
}
