package on2012_03.on2012_2_21.binarypolynomialdivone;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int findCoefficient(int[] a, long m, long k)",
			"on2012_03.on2012_2_21.binarypolynomialdivone.BinaryPolynomialDivOne",
			"1,0,1;;3;;4;;1;;true::1,0,1;;3;;5;;0;;true::0,0,1,1,0,1;;7;;15;;1;;true::1;;1;;0;;1;;true::1,0,1,1,1,1,0,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1,1,0,1,0,0,1,1;;2229508454872453;;96130299655104846;;1;;true"))
		{
			Assert.fail();
		}
	}
}
