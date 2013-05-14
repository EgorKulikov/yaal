package on2011_10.on2011_9_29.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taska.TaskA",
			"MULTI_NUMBER",
			"3/__bdac 11/__abcd 5/__hjbrl 120;;Case 1/: abcd/__Case 2/: acdb/__Case 3/: lrbjh"))
		{
			Assert.fail();
		}
	}
}
