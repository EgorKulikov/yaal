package on2012_05.on2012_4_12.heavybooks;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int[] findWeight(int[] books, int[] moves)",
			"on2012_05.on2012_4_12.heavybooks.HeavyBooks",
			"5,2,3,1;;3,2,1,1,1;;3,7;;true::10,100,1000;;2,3;;110,0;;true::155870,565381;;1,1,1;;0,565381;;true::500,500,500,500;;4,1,1,3,2;;500,1500;;true::1,1,1,1,1000000;;1;;0,1000000;;true::1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20;;20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1;;110,100;;true"))
		{
			Assert.fail();
		}
	}
}
