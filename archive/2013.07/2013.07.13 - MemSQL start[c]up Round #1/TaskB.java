package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long gameCount = in.readLong();
		List<Long> answer = new ArrayList<Long>();
		for (long i = 0; gameCount >= i; i = i * 2 + 1) {
			long left = 1;
			long right = Math.min(i == 0 ? gameCount : gameCount / i, Math.round(Math.floor(Math.sqrt(gameCount * 2))) + 1);
			while (left <= right) {
				long middle = (left + right) >> 1;
				long value = i * middle + ((middle * (middle - 1)) >> 1);
				if (value == gameCount) {
					if (middle % 2 == 1)
						answer.add(middle * (i + 1));
					break;
				} else if (value < gameCount)
					left = middle + 1;
				else
					right = middle - 1;
			}
		}
		Collections.sort(answer);
		if (answer.isEmpty())
			answer.add(-1L);
		for (long i : answer)
			out.printLine(i);
    }
}
