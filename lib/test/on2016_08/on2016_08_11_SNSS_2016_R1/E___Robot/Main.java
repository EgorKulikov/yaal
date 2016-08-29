package on2016_08.on2016_08_11_SNSS_2016_R1.E___Robot;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2016_08/on2016_08_11_SNSS_2016_R1/E___Robot/E - Robot.task"))
			Assert.fail();
	}
}
