package on2011_11.on2011_10_26.taska0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taska0.TaskA",
			"MULTI_NUMBER",
			"4/__123/__213/__31312/__311111;;9/__72/__504/__504;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
