package on2012_08.on2012_7_12.jackvsjill;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_12.jackvsjill.JackVsJill",
			"SINGLE",
			"6/__2/__3/__2/__-1/__-1/__1/__-1;;3/__3/__2;;true::10/__5/__3/__6/__1/__4/__2/__9/__3/__1/__0/__-1;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
