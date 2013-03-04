package on2012_08.on2012_7_12.frequentterms;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_12.frequentterms.FrequentTerms",
			"SINGLE",
			"14/__Fee/__Fi/__Fo/__Fum/__Fee/__Fo/__Fee /__Fee/__Fo/__Fi/__Fi/__Fo/__Fum/__Fee/__3;;Fee/__Fo/__Fi;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
