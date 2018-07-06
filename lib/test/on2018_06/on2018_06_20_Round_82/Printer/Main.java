package on2018_06.on2018_06_20_Round_82.Printer;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2018_06/on2018_06_20_Round_82/Printer/Printer.json"))
			Assert.fail();
	}
}
