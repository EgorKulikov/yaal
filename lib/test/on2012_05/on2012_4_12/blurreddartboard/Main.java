package on2012_05.on2012_4_12.blurreddartboard;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int minThrows(int[] points, int P)",
			"on2012_05.on2012_4_12.blurreddartboard.BlurredDartboard",
			"0,3,4,0,0;;8;;2;;true::0,0,0,0,0;;15;;5;;true::4,7,8,1,3,2,6,5;;2012;;252;;true::0,0,5,0,0,0,1,3,0,0;;2012;;307;;true::0,2,0,0,0,0,0,0,0,9,0,0,6,0,0,0,4,0,0,0;;1000000000;;84656087;;true"))
		{
			Assert.fail();
		}
	}
}
