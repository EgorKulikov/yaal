package on2015_08.on2015_08_07_August_Challenge_2015.Chef_and_insomnia;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_08/on2015_08_07_August_Challenge_2015/Chef_and_insomnia/Chef and insomnia.task"))
			Assert.fail();
	}
}
