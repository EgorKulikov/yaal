package on2012_05.on2012_4_1.jewelsandstones;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_1.jewelsandstones.JewelsAndStones",
			"MULTI_NUMBER",
			"4/__abc/__abcdef/__aA/__abAZ/__aaa/__a/__what/__none/__;;3/__2/__1/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
