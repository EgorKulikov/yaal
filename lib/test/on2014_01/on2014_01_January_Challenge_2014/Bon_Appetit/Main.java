package on2014_01.on2014_01_January_Challenge_2014.Bon_Appetit;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_01/on2014_01_January_Challenge_2014/Bon_Appetit/Bon Appetit.task"))
			Assert.fail();
	}
}
