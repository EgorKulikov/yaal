package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class APTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(250, 30000, 10000);
		Random random = new Random(239);
		for (int i = 0; i < 30000; i++) {
			int from = random.nextInt(250) + 1;
			int to = random.nextInt(250) + 1;
			while (from == to) {
				from = random.nextInt(250) + 1;
				to = random.nextInt(250) + 1;
			}
			out.printLine(from, to, random.nextInt(10000) + 1);
		}
		for (int i = 1; i <= 10000; i++)
			out.printLine(i);
		return Collections.singleton(new Test(sw.toString()));
	}
}
