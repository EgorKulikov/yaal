package on2011_12.on2011_11_25.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_25.taska.TaskA",
			"SINGLE",
			"1 1/__day/__may/__sun/__fun/__;;aabb/__;;true::1 1/__day/__may/__gray/__way/__;;aaaa/__;;true::2 1/__a/__a/__a/__a/__a/__a/__e/__e/__;;aabb/__;;true::2 1/__day/__may/__sun/__fun/__test/__hill/__fest/__thrill/__;;NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
