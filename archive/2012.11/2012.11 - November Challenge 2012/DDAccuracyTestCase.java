package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class DDAccuracyTestCase implements TestProvider {
	boolean[] isGood = new boolean[1000001];

	{
		for (int i = 0; i <= 1000000; i++) {
			int mask = 0;
			isGood[i] = true;
			int ii = i;
			while (ii != 0) {
				int digit = ii % 10;
				if ((mask >> digit & 1) != 0) {
					isGood[i] = false;
					break;
				}
				mask += 1 << digit;
				ii /= 10;
			}
		}
	}

	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		StringWriter swAns = new StringWriter();
		OutputWriter outAns = new OutputWriter(swAns);
		Random random = new Random(239);
		out.printLine(1000);
		for (int i = 0; i < 1000; i++) {
			int a = Math.abs(random.nextInt()) % 1000000 + 1;
			int b = Math.abs(random.nextInt()) % 1000000 + 1;
			int answer = 0;
			for (int j = Math.min(a, b); j <= Math.max(a, b); j++) {
				if (isGood[j])
					answer++;
			}
			out.printLine(Math.min(a, b), Math.max(a, b));
			outAns.printLine(answer);
		}
		return Collections.singleton(new Test(sw.toString(), swAns.toString()));
	}
}
