package on2012_08.on2012_7_7.conference;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_7.conference.Conference",
			"SINGLE",
			"5 3 2/__2 1/__3 1/__3 2/__4 3/__2 1;;No/__Yes;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
