package on2012_02.on2012_1_11.taske;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class TaskEChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringBuilder test = new StringBuilder();
		Random rand = new Random(239);
		for (int i = 0; i < 100000; i++)
			test.append((char)('A' + rand.nextInt(26)));
		test.append("\n100\n");
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 1000; j++)
				test.append((char)('A' + rand.nextInt(26)));
			test.append("\n");
		}
		return Collections.singleton(new Test(test.toString(), "0\n", 0));
	}
}
