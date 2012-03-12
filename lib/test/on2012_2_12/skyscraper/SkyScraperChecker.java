package on2012_2_12.skyscraper;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class SkyScraperChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringBuilder builder = new StringBuilder();
		builder.append("18 18\n");
		for (int i = 0; i < 18; i++)
			builder.append("1\n");
		StringBuilder output = new StringBuilder();
		output.append("1\n18");
		for (int i = 1; i <= 18; i++)
			output.append(' ').append(i);
		output.append('\n');
		return Collections.singleton(new Test(builder.toString(), output.toString(), 0));
	}
}
