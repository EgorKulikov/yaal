package on2012_08.on2012_7_12.palindromemagic;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_12.palindromemagic.PalindromeMagic",
			"MULTI_NUMBER",
			"3/__4 9/__7 50/__9 120/__;;1881/__1049401/__101191101/__;;true::1/__1 2/__;;2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
