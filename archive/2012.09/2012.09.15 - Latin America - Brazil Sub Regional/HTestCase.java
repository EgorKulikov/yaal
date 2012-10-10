package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;

public class HTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(10000);
		for (int i = 2; i <= 10000; i += 2) {
			out.printLine(i - 1, i);
			if (i > 2)
				out.printLine(i - 2, i);
		}
		for (int i = 2; i <= 10000; i += 2) {
			out.printLine(i - 1, i);
			if (i > 2)
				out.printLine(i - 2, i);
		}
		return Collections.singleton(new Test(sw.toString(), "S"));
	}
}
