package on2011_11.on2011_10_29.task1640;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_29.task1640.Task1640",
			"SINGLE",
			"7/__1 1/__1 5/__3 6/__5 3/__8 0/__9 5/__5 9;;5 4 5;;true::2/__0 2/__2 0/__;;1 1 1.41421356237309/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
