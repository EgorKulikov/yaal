package on2012_05.on2012_4_5.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_5.taskg.TaskG",
			"SINGLE",
			"abc/__abd/__acc/__bbb/__aba/__--/__abc acc/__abc bbb/__acc abd/__bbb abc/__abd abc/__aba abc;;Yes/__No/__Yes/__No/__Yes/__Yes;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
