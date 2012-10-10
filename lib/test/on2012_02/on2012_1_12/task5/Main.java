package on2012_02.on2012_1_12.task5;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task5.Task5",
			"MULTI_NUMBER",
			"5/__4 10/__1 15/__6 5/__17 8/__9 9;;355/__120/__209/__3732/__1344;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
