package old.approved.test1638902021;

import net.egork.utils.old.io.stringinputreader.StringInputReader;
import net.egork.utils.old.test.Test;
import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class Test1638902021 {
	@org.junit.Test
	public void test() throws Exception {
		Locale.setDefault(Locale.US);
		Collection<Test> tests = Arrays.asList(Tests.TESTS);
		tests.addAll(MainChecker.generateTests());
		for (Test test : tests) {
			StringWriter output = new StringWriter();
			try {
				Main.run(new StringInputReader(test.getInput()), new PrintWriter(output));
				String verdict = MainChecker.check(new StringInputReader(test.getInput()),
					new StringInputReader(test.getExpectedOutput()), new StringInputReader(output.toString()));
				if (verdict != null) {
					System.err.println("Failed with wrong answer: " + verdict);
					System.err.println("Input:");
					System.err.println(test.getInput());
					System.err.println("Expected output:");
					System.err.println(test.getExpectedOutput());
					System.err.println("Actual output:");
					System.err.println(output.toString());
					Assert.fail();
				}
			} catch (Throwable e) {
				System.err.println("Failed with runtime error");
				System.err.println("Input:");
				System.err.println(test.getInput());
				System.err.println("Expected output:");
				System.err.println(test.getExpectedOutput());
				System.err.println("Exception:");
				e.printStackTrace();
				Assert.fail();
			}
		}
	}
}