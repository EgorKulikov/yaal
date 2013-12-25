package on2012_01.on2012_0_1.rock;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_1.rock.Rock",
			"SINGLE",
			"5/__1/1...../__16/32/__1/2/__30/32/__1/32;;63 16;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
