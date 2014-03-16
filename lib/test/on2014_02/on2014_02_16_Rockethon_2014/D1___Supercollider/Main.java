package on2014_02.on2014_02_16_Rockethon_2014.D1___Supercollider;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_02/on2014_02_16_Rockethon_2014/D1___Supercollider/D1 - Supercollider.task"))
			Assert.fail();
	}
}
