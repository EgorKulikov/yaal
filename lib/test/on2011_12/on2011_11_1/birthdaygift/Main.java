package on2011_12.on2011_11_1.birthdaygift;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_1.birthdaygift.BirthdayGift",
			"MULTI_NUMBER",
			"1/__20 2 2/__2 1/__1 1/__0 2/__3 0/__;;18/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
