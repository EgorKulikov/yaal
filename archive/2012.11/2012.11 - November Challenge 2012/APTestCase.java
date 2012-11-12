package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class APTestCase implements TestProvider {
	static long[] aa;

	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		Random random = new Random(239);
		out.printLine(10000);
		int[] a = new int[10000];
		for (int i = 0; i < 10000; i++)
			out.printLine((a[i] = random.nextInt(10)) + 1);
		long answer = 0;
		long[] count = new long[10];
		for (int i : a)
			count[i]++;
		aa = new long[10000];
		for (int i = 0; i < 10000; i++) {
			count[a[i]]--;
			for (int j = 0; j < i; j++) {
				int right = 2 * a[i] - a[j];
				if (right >= 0 && right < 10)
					aa[i] += count[right];
			}
			answer += aa[i];
		}
		return Collections.singleton(new Test(sw.toString(), Long.toString(answer)));
	}
}
