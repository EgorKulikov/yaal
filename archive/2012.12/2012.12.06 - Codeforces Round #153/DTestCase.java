package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class DTestCase implements TestProvider {

	public static final int COUNT = 20;

	public Collection<Test> createTests() {
		List<Test> list = new ArrayList<Test>();
		Random rnd = new Random(239);
		for (int i = 0; i < 20; i++) {
			long[] numbers = new long[COUNT];
			for (int j = 0; j < COUNT; j++)
				numbers[j] = Math.abs(rnd.nextLong()) % ((long)(1e18 + 1));
			long xor = 0;
			for (long j : numbers)
				xor ^= j;
			long bestSum = -1;
			long bestSecond = -1;
			int mask = 0;
			for (int j = 0; j < (1 << COUNT); j++) {
				long curSecond = 0;
				for (int k = 0; k < COUNT; k++) {
					if ((j >> k & 1) == 1) {
						curSecond ^= numbers[k];
					}
				}
				long curSum = curSecond + (curSecond ^ xor);
				if (curSum > bestSum || curSum == bestSum && curSecond > bestSecond) {
					bestSum = curSum;
					bestSecond = curSecond;
					mask = j;
				}
			}
			int[] answer = new int[COUNT];
			for (int j = 0; j < COUNT; j++) {
				if ((mask >> j & 1) == 1)
					answer[j] = 2;
				else
					answer[j] = 1;
			}
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			out.printLine(COUNT);
			for (int j = 0; j < COUNT; j++)
				out.print(numbers[j] + " ");
			out.printLine();
			String input = sw.toString();
			sw = new StringWriter();
			out = new OutputWriter(sw);
			out.printLine(answer);
			list.add(new Test(input, sw.toString()));
		}
		return list;
	}
}
