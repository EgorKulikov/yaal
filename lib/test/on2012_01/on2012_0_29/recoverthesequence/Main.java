package on2012_01.on2012_0_29.recoverthesequence;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_29.recoverthesequence.RecoverTheSequence",
			"MULTI_NUMBER",
			"5/__2/__1/__2/__2/__4/__12212/__5/__1122211/__10/__121221212111122121212;;Case #1/: 994/__Case #2/: 1024/__Case #3/: 987041/__Case #4/: 570316/__Case #5/: 940812;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
