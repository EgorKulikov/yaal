package on2013_01.on2013_01_25_.SortTest;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_01/on2013_01_25_/SortTest/SortTest.task"))
			Assert.fail();
	}
}
