package on2012_01.on2012_0_28.tricir;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.tricir.TriCir",
			"SINGLE",
			"2/__1 3/__2 3 4 6;;1;;true::6/__1 2/__1 3/__1 4/__2 3 3 4/__2 4 4 5/__2 5 5 6;;2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
