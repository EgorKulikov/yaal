package on2012_04.on2012_3_14.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_14.taske.TaskE",
			"SINGLE",
			"1 0 0/__10 10 10/__100 100/__;;1989.97487421;;true::1 0 1/__10 10 10/__100 100/__;;3979.94974843;;true::5 5 10/__100 100 100/__100 100/__;;2166889.9972498626;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
