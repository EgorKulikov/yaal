package on2011_12.on2011_11_12.tasko;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.tasko.TaskO",
			"SINGLE",
			"3 4/__1 2 3 4/__5 6 7 8/__9 10 11 12/__;;4 3/__9 5 1/__10 6 2/__11 7 3/__12 8 4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
