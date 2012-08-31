package on2012_07.on2012_6_9.pointywizardhats;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int getNumHats(int[] topHeight, int[] topRadius, int[] bottomHeight, int[] bottomRadius)",
			"on2012_07.on2012_6_9.pointywizardhats.PointyWizardHats",
			"30;;3;;3;;30;;1;;true::4,4;;4,3;;5,12;;5,4;;1;;true::3;;3;;1,1;;2,4;;1;;true::10,10;;2,5;;2,9;;3,6;;2;;true::3,4,5;;5,4,3;;3,4,5;;3,8,5;;2;;true::1,2,3,4,5;;2,3,4,5,6;;2,3,4,5,6;;1,2,3,4,5;;0;;true::123,214,232,323,342,343;;123,123,232,123,323,434;;545,322,123,545,777,999;;323,443,123,656,767,888;;5;;true::999,999,999,10000,10000,10000;;10000,10000,10000,1,2,3;;2324,2323,234,5454,323,232;;1,2,3222,434,5454,23;;3;;true"))
		{
			Assert.fail();
		}
	}
}
