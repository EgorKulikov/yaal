package on2011_12.on2011_11_25.taskd;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.util.Collection;
import java.util.Collections;

public class TaskDChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
//		StringBuilder input = new StringBuilder();
//		Random random = new Random(239);
//		input.append("20 20\n");
//		for (int i = 0; i < 20; i++) {
//			for (int j = 0; j < 20; j++) {
//				int value = random.nextInt(2) + 1;
//				if (value == 0)
//					input.append("X");
//				else if (value == 1)
//					input.append("L");
//				else if (value == 2)
//					input.append("R");
//			}
//			input.append("\n");
//		}
//		return Collections.singleton(new Test(input.toString(), "", 0));
		return Collections.emptyList();
	}
}
