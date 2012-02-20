package on2012_0_31.buildingconstruction;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_31.buildingconstruction.BuildingConstruction",
			"MULTI_NUMBER",
			"1/__3/__1 2 3/__10 100 1000;;120;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
