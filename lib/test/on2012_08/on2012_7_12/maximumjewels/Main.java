package on2012_08.on2012_7_12.maximumjewels;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_12.maximumjewels.MaximumJewels",
			"SINGLE",
			"4,4/__0,23,20,-32/__13,14,44,-44/__23,19,41,9/__46,27,20,0/__;;129/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
