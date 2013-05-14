package on2012_02.on2012_1_22.dengklekcountingformations;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int numFormations(int N, int M, int K)",
			"on2012_02.on2012_1_22.dengklekcountingformations.DengklekCountingFormations",
			"2;;2;;2;;10;;true::1;;1;;58;;58;;true::5;;3;;2;;0;;true::3;;5;;7;;894953467;;true::7;;47;;74;;778075142;;true"))
		{
			Assert.fail();
		}
	}
}
