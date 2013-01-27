package on2012_02.on2012_1_22.dengklekbuildingroads;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int numWays(int N, int M, int K)",
			"on2012_02.on2012_1_22.dengklekbuildingroads.DengklekBuildingRoads",
			"3;;4;;1;;3;;true::4;;3;;3;;4;;true::2;;1;;1;;0;;true::5;;0;;3;;1;;true::10;;20;;5;;26964424;;true"))
		{
			Assert.fail();
		}
	}
}
