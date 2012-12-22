package on2012_05.on2012_4_27.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_27.taske.TaskE",
			"SINGLE",
			"1/__;;2 2 3/__;;true::60000/__;;4868977 4868977 9737953;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
