package on2011_10.on2011_9_30.chronicle;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_30.chronicle.Chronicle",
			"SINGLE",
			"29/02/04;;29/02/04/__29/04/02/__02/04/29/__04/02/29::01/01/01;;01/01/01::99/99/99;;No such date"))
		{
			Assert.fail();
		}
	}
}
