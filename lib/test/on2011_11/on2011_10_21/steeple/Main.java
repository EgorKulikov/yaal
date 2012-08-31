package on2011_11.on2011_10_21.steeple;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_21.steeple.Steeple",
			"SINGLE",
			"3/__4 5 10 5/__6 2 6 12/__8 3 8 5;;2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
