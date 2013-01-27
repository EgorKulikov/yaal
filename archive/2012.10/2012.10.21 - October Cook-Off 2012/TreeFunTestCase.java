package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class TreeFunTestCase implements TestProvider {
	public Collection<Test> createTests() {
		Random random = new Random(239);
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(5);
		int[] array = new int[5];
		for (int i = 0; i < 5; i++) {
			out.printLine(50000, 50000);
			for (int j = 1; j < 50000; j++)
				out.printLine(j + 1, random.nextInt(j) + 1);
			for (int j = 0; j < 50000; j++) {
				int count = random.nextInt(4) + 2;
				while (true) {
					for (int k = 0; k < count; k++)
						array[k] = random.nextInt(50000) + 1;
					boolean good = true;
					for (int k = 0; k < count; k++) {
						for (int l = k + 1; l < count; l++) {
							if (array[k] == array[l])
								good = false;
						}
					}
					if (good)
						break;
				}
				out.print(count);
				for (int k = 0; k < count; k++)
					out.print(" " + array[k]);
				out.printLine();
			}
		}
//		return Collections.singleton(new Test(sw.toString()));
		return Collections.emptyList();
	}
}
