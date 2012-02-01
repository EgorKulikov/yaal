package on2012_0_28.nicknames;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_28.nicknames.Nicknames",
			"MULTI_NUMBER",
			"2/__xyyx/__xxy/__yxy/__z/__zt/__zz;;Yes/__No;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
