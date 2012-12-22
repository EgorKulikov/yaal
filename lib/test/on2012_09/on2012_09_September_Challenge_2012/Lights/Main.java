package on2012_09.on2012_09_September_Challenge_2012.Lights;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2012_09/on2012_09_September_Challenge_2012/Lights/Lights.task"))
			Assert.fail();
	}
}
