package on2014_07.on2014_07_04_July_Challenge_2014.Chef_and_Frogs;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_07/on2014_07_04_July_Challenge_2014/Chef_and_Frogs/Chef and Frogs.task"))
			Assert.fail();
	}
}
