package on2012_04.on2012_3_23.subsets;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class SubsetsChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringWriter sw = maxTest();
		return Arrays.asList(new Test(sw.toString(), Integer.toString((1 << 19) - 1), 0),
			new Test(randomTest(), "2", 0), new Test(otherMaxTest(), "0", 0));
	}

	private String randomTest() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(20);
		Random rnd = new Random(239);
		for (int i = 0; i < 20; i++)
			out.printLine(rnd.nextInt(100000000) + 1);
		out.flush();
		return sw.toString();
	}

	private String otherMaxTest() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(20);
		for (int i = 0; i < 20; i++)
			out.printLine(1 << i);
		out.flush();
		return sw.toString();
	}

	private StringWriter maxTest() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(20);
		for (int i = 0; i < 20; i++)
			out.printLine(2);
		out.flush();
		return sw;
	}
}
