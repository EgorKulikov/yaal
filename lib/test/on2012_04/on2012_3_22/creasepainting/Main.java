package on2012_04.on2012_3_22.creasepainting;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_22.creasepainting.CreasePainting",
			"SINGLE",
			"5/__R 100/__U 10/__L 120/__D 10/__R 200/__;;100/__10/__120/__10/__99/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
