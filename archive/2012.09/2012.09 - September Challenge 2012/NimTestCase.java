package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class NimTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(1000);
		Random random = new Random(239);
		for (int i = 0; i < 1000; i++) {
			while (true) {
				int count = random.nextInt(991) + 10;
				int limit = random.nextInt(56) + 5;
				long[] numbers = new long[count];
				long xor = 0;
				for (int j = 0; j < count - 1; j++) {
					while ((numbers[j] = (random.nextLong() & ((1L << limit) - 1))) == 0);
					xor ^= numbers[j];
				}
				if (xor != 0) {
					numbers[count - 1] = xor;
					out.printLine(count);
					out.printLine(Array.wrap(numbers).toArray());
					break;
				}
			}
		}
		return Collections.singleton(new Test(sw.toString()));
	}
}
