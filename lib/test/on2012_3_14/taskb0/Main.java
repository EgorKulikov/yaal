package on2012_3_14.taskb0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_14.taskb0.TaskB",
			"SINGLE",
			"5/__vasya 100/__vasya 200/__artem 100/__kolya 200/__igor 250/__;;4/__artem noob/__igor pro/__kolya random/__vasya random/__;;true::3/__vasya 200/__kolya 1000/__vasya 1000/__;;2/__kolya pro/__vasya pro/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
