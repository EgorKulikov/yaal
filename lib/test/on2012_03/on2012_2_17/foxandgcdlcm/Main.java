package on2012_03.on2012_2_17.foxandgcdlcm;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("long get(long G, long L)",
			"on2012_03.on2012_2_17.foxandgcdlcm.FoxAndGCDLCM",
			"2;;20;;14;;true::5;;8;;-1;;true::1000;;100;;-1;;true::100;;1000;;700;;true::10;;950863963000;;6298430;;true"))
		{
			Assert.fail();
		}
	}
}
