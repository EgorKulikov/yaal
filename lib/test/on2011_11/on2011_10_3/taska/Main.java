package on2011_11.on2011_10_3.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_3.taska.TaskA",
			"SINGLE",
			"abc/__;;YES/__abc/__;;true::abcd/__;;NO/__;;true::xxxyxxx/__;;YES/__xxxxxxy/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
