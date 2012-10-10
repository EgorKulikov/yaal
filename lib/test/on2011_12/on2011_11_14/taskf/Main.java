package on2011_12.on2011_11_14.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_14.taskf.TaskF",
			"SINGLE",
			"2 10/__10 100/__9 80;;1 100/__1;;true::5 100/__80 1000/__50 550/__50 550/__50 550/__50 550;;2 1100/__2 3;;true::6 100/__80 1000/__50 550/__50 550/__50 550/__50 550/__100 1100;;1 1100/__6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
