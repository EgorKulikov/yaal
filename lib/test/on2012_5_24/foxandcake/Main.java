package on2012_5_24.foxandcake;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("String ableToDivide(int n, int m, int[] x, int[] y)",
			"on2012_5_24.foxandcake.FoxAndCake",
			"2;;4;;1,1,1,1,2,2,2;;1,2,3,4,2,3,4;;Yes;;true::2;;4;;1,1,2,1,2,1,2;;1,2,2,3,3,4,4;;No;;true::6;;6;;1,1,3,4,3,4,5;;2,6,4,5,5,4,2;;Yes;;true::999999999;;999999999;;500000000,1,1,1,999999999,999999999,999999999;;500000000,1,2,3,999999997,999999998,999999999;;Yes;;true::1000000000;;1000000000;;500000000,1,1,2,999999998,999999999,999999999;;500000000,1,2,1,999999999,999999998,999999999;;No;;true::3;;4;;1,1,2,3,2,3,3;;1,2,3,4,1,2,3;;No;;true::1000000000;;1000000000;;10,20,30,40,50,60,70;;10,20,30,40,50,60,70;;Yes;;true"))
		{
			Assert.fail();
		}
	}
}
