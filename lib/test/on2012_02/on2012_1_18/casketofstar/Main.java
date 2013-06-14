package on2012_02.on2012_1_18.casketofstar;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int maxEnergy(int[] weight)",
			"on2012_02.on2012_1_18.casketofstar.CasketOfStar",
			"1,2,3,4;;12;;true::100,2,1,3,100;;10400;;true::2,2,7,6,90,5,9;;1818;;true::477,744,474,777,447,747,777,474;;2937051;;true::1,1,1,1,1,1,1,1,1,1,1,1,1,1,1;;13;;true"))
		{
			Assert.fail();
		}
	}
}
