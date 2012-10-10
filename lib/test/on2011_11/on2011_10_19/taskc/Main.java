package on2011_11.on2011_10_19.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_19.taskc.TaskC",
			"SINGLE",
			"3 2 3 5 2 4 5;;10;;true::4 6 2 3 4 1 1;;1;;true::3 358572 83391967 82 3 50229961 1091444 8863;;000012028;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
