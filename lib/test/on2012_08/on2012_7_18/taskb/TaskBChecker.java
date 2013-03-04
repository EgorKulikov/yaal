package on2012_08.on2012_7_18.taskb;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskBChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		List<Test> tests = new ArrayList<Test>();
//		for (int i = 1; i <= 1000; i++)
//			tests.add(new Test(i + " 1000000", "", 0));
//		for (int i = 1100; i <= 1000000; i += 100)
//			tests.add(new Test(i + " 1000000", "", 0));
		return tests;
	}
}
