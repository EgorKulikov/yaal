package on2012_03.on2012_2_17.kingxnewcurrency;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int howMany(int A, int B, int X)",
			"on2012_03.on2012_2_17.kingxnewcurrency.KingXNewCurrency",
			"5;;8;;5;;5;;true::8;;4;;2;;-1;;true::7;;4;;13;;1;;true::47;;74;;44;;2;;true::128;;96;;3;;65;;true::200;;199;;2;;100;;true"))
		{
			Assert.fail();
		}
	}
}
