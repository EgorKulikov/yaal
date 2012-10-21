package on2012_04.on2012_3_3.pdsnumber;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class PDSNumberChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringBuilder input = new StringBuilder();
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			input.append("1000000000\n");
			output.append("1439423451\n");
		}
		input.append("0\n");
		return Collections.singleton(new Test(input.toString(), output.toString(), 0));
	}
}
