package on2012_07.on2012_6_7.kingdomandcities;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int howMany(int N, int M, int K)",
			"on2012_07.on2012_6_7.kingdomandcities.KingdomAndCities",
			"3;;0;;3;;1;;true::4;;1;;4;;9;;true::5;;2;;11;;0;;true::5;;0;;8;;45;;true::10;;2;;20;;150810825;;true::3;;2;;3;;1;;true::50;;2;;50;;654698051;;true"))
		{
			Assert.fail();
		}
	}
}
