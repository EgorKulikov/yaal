package on2012_03.on2012_2_31.martianthief;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_31.martianthief.MartianThief",
			"SINGLE",
			"3 20/__10 10 10/__5 8 12/__0 1 1/__;;17/__;;true::5 1/__0 0 0 0 1/__1 2 3 4 5/__0 0 0 0 0/__;;15;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
