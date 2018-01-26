package on2017_07.on2017_07_08_IPSC_2017.J;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2017_07/on2017_07_08_IPSC_2017/J/J.task"))
			Assert.fail();
	}
}
