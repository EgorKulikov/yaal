package on2011_10.on2011_9_27.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taska.TaskA",
			"SINGLE",
			"3 2/__1 4 3;;1.570796327/__6.283185307/__4.712388980"))
		{
			Assert.fail();
		}
	}
}
