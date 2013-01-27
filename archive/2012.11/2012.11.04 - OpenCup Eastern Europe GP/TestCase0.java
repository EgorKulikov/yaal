package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class TestCase0 implements TestProvider {
	public Collection<Test> createTests() {
		StringBuilder b = new StringBuilder();
		int n = 100000;
		int m = 100000;
		b.append(n);
		b.append(" ");
		b.append(m);
		b.append("\n");
		Random random = new Random(54817541);
		for (int i = 0; i < n + m; ++i) {
			int mx = (i < n) ? (int) 1e7 : (int) 1e8;
			b.append(random.nextInt((int) 2 * mx + 1) - (int) mx);
			b.append(" ");
			b.append(random.nextInt((int) 2 * mx + 1) - (int) mx);
			b.append("\n");
		}
		return Collections.singleton(new Test(b.toString()));
	}
}
