package on2012_6_5.equivalentsuffixtries;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_6_5.equivalentsuffixtries.EquivalentSuffixTries",
			"MULTI_NUMBER",
			"5/__a/__aa/__abc/__aba/__helloworld/__;;26/__26/__15600/__1300/__6221124/__;;true::2/__abab/__abaa;;1300/__650/__;;true::1/__aaba/__;;1300/__;;true::1/__abbaba;;650;;true::1/__aabaaa;;650;;true::1/__abbaab;;1300;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
