package on2012_08.on2012_08_SnarkNews_Summer_Series__6.repair;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2012_08/on2012_08_SnarkNews_Summer_Series__6/repair/Repair.task"))
			Assert.fail();
	}
}
