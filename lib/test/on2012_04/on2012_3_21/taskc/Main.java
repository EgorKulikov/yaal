package on2012_04.on2012_3_21.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_21.taskc.TaskC",
			"SINGLE",
			"9/__8/__1 2/__1 3/__2 3/__4 5/__6 7/__7 8/__8 9/__9 6/__2/__1 6/__7 9;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
