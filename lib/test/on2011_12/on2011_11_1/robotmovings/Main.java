package on2011_12.on2011_11_1.robotmovings;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_1.robotmovings.RobotMovings",
			"MULTI_EOF",
			"4 2/__4 3/__5 3/__0 0/__;;4/__8/__18/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
