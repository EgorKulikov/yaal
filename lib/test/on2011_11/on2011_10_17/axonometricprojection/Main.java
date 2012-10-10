package on2011_11.on2011_10_17.axonometricprojection;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int howManyWays(int[] heightsOfLeftView, int[] heightsOfFrontView)",
			"on2011_11.on2011_10_17.axonometricprojection.AxonometricProjection",
			"{1,1};;{1,1};;7;;true::{50,50,50,50,524};;{524,524};;104060401;;true::{1,2,3,4,5,6,7,8,9,10};;{1,2,3,4,5,6,7,8,9,10,11};;0;;true::{5,2,4,1};;{5,2,4,0,1};;429287;;true::{5,2,4,52,24,524};;{0,4,20,24,500,504,520,524};;97065655;;true"))
		{
			Assert.fail();
		}
	}
}
