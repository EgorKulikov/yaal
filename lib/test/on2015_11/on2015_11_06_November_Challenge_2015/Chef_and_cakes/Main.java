package on2015_11.on2015_11_06_November_Challenge_2015.Chef_and_cakes;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_11/on2015_11_06_November_Challenge_2015/Chef_and_cakes/Chef and cakes.task"))
			Assert.fail();
	}
}
