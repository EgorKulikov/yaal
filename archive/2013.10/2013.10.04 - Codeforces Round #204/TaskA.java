package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() * 2;
		int[] array = new int[count];
		for (int i = 0; i < count; i++)
			array[i] = (int) Math.round(in.readDouble() * 1000);
		for (int i = 0; i < count; i++)
			array[i] %= 1000;
		int answer = (int) ArrayUtils.sumArray(array);
		int min = Math.max(count / 2 - ArrayUtils.count(array, 0), 0);
		int max = Math.min(count / 2, count - ArrayUtils.count(array, 0));
		if (answer >= min * 1000 && answer <= max * 1000) {
			answer %= 1000;
			if (answer > 500)
				answer = 1000 - answer;
		} else if (answer < min * 1000)
			answer = min * 1000 - answer;
		else
			answer -= max * 1000;
		out.printFormat("%.3f\n", answer / 1000d);
    }
}
