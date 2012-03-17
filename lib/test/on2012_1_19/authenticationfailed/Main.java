package on2012_1_19.authenticationfailed;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_19.authenticationfailed.AuthenticationFailed",
			"MULTI_NUMBER",
			"3/__2 1/__aaa/__3 1/__abcd/__4 2/__ababab/__;;0/__3/__10/__;;true::1/__4 4/__aaaabbbb/__;;4;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
