package on2011_12.on2011_11_16.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_16.taskd.TaskD",
			"SINGLE",
			"abacaba/__1/__;;0/__abacaba/__;;true::abdcaba/__2/__;;1/__abdcdba/__;;true::abdcaba/__5/__;;0/__a+b+d+c+aba/__;;true::abacababababbcbabcd/__3/__;;1/__abacaba+babab+bcbabcb/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
