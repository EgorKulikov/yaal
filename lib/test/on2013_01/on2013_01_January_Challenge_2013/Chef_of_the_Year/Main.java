package on2013_01.on2013_01_January_Challenge_2013.Chef_of_the_Year;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_01/on2013_01_January_Challenge_2013/Chef_of_the_Year/Chef of the Year.task"))
			Assert.fail();
	}
}
