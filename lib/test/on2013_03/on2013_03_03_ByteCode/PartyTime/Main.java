package on2013_03.on2013_03_03_ByteCode.PartyTime;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_03/on2013_03_03_ByteCode/PartyTime/PartyTime.task"))
			Assert.fail();
	}
}
