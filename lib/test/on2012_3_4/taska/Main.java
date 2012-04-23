package on2012_3_4.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_4.taska.TaskA",
			"SINGLE",
			"4/__00209/__00219/__00999/__00909/__;;2/__;;true::2/__1/__2/__;;0/__;;true::3/__77012345678999999999/__77012345678901234567/__77012345678998765432/__;;12/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
