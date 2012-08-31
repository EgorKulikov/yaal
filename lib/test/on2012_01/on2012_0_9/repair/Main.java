package on2012_01.on2012_0_9.repair;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_9.repair.Repair",
			"SINGLE",
			"5 5 1 5/__1 2/__1 3/__2 4/__3 4/__4 5;;1;;true::5 5 1 4/__1 2/__2 3/__3 4/__4 5/__5 1;;2;;true::5 10 1 4/__1 2/__2 4/__4 3/__1 3/__1 5/__2 5/__4 5/__3 5/__1 4/__2 3;;0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
