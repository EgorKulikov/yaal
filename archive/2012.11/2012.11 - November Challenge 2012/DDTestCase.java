package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class DDTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		Random random = new Random(239);
		out.printLine(200000);
		for (int i = 0; i < 200000; i++) {
			long a = Math.abs(random.nextLong()) % 9876543210L + 1;
			long b = Math.abs(random.nextLong()) % 9876543210L + 1;
			out.printLine(Math.min(a, b), Math.max(a, b));
		}
//		return Collections.singleton(new Test(sw.toString()));
		return Collections.emptyList();
	}
}
