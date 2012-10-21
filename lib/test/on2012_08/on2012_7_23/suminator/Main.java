package on2012_08.on2012_7_23.suminator;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int findMissing(int[] program, int wantedResult)",
			"on2012_08.on2012_7_23.suminator.Suminator",
			"7,-1,0;;10;;3;;true::100,200,300,0,100,-1;;600;;0;;true::-1,7,3,0,1,2,0,0;;13;;0;;true::-1,8,4,0,1,2,0,0;;16;;-1;;true::1000000000,1000000000,1000000000,1000000000,-1,0,0,0,0;;1000000000;;-1;;true::7,-1,3,0;;3;;-1;;true"))
		{
			Assert.fail();
		}
	}
}
