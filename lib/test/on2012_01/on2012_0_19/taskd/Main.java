package on2012_01.on2012_0_19.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_19.taskd.TaskD",
			"MULTI_NUMBER",
			"2/__ANBOBNA/__iAmACoolCompany/__;;YES/__NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
