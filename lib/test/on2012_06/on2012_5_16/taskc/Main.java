package on2012_06.on2012_5_16.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_16.taskc.TaskC",
			"SINGLE",
			"4 2 2/__+/__+/__+/__- 2;;1 3/__1 1;;true::8 3 2/__+/__+/__+/__+/__+/__+/__- 2/__- 5;;1 4/__1 1/__2 3 6;;true::12 4 3/__+/__+/__+/__+/__+/__+/__+/__+/__-2/__-3/__-7/__+;;2 1 5/__2 6 9/__1 4/__1 8/__;;true::2 2 2/__+/__- 1/__;;0/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
