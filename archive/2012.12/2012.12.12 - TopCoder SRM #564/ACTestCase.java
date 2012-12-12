package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TopCoderTestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ACTestCase implements TopCoderTestProvider {
	public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 1; i <= 100; i++) {
			long[] count = new long[i];
			for (int r = 0; r <= i; r++) {
				for (int g = 0; r + g <= i; g++) {
					int b = i - r - g;
					int cr = r;
					int cg = g;
					int cb = b;
					int j = 0;
					while (cr != 0) {
						cr--;
						count[j++]++;
						if (cg != 0) {
							cg--;
							j++;
						}
						if (cb != 0) {
							cb--;
							j++;
						}
					}
				}
			}
			for (int j = 0; j < i; j++)
				tests.add(new NewTopCoderTest(new Object[]{i, j + 1}, count[j]));
		}
		return tests;
	}
}
