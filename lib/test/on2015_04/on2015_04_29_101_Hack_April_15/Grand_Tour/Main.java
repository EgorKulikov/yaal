package on2015_04.on2015_04_29_101_Hack_April_15.Grand_Tour;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_04/on2015_04_29_101_Hack_April_15/Grand_Tour/Grand Tour.task"))
			Assert.fail();
	}
}
