package on2011_10.on2011_9_27.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taskd.TaskD",
			"MULTI_NUMBER",
			"1/__ab bc 3;;a/:1/__b/:3/__c/:2/__d/:0/__e/:0/__f/:0/__g/:0/__h/:0/__i/:0/__j/:0/__k/:0/__l/:0/__m/:0/__n/:0/__o/:0/__p/:0/__q/:0/__r/:0/__s/:0/__t/:0/__u/:0/__v/:0/__w/:0/__x/:0/__y/:0/__z/:0"))
		{
			Assert.fail();
		}
	}
}
