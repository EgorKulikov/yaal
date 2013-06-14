package on2013_03.on2013_03_24_March_Cook_off_2013.Bishops;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_03/on2013_03_24_March_Cook_off_2013/Bishops/Bishops.task"))
			Assert.fail();
	}
}
