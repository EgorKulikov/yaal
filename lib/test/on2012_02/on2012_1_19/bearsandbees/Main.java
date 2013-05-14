package on2012_02.on2012_1_19.bearsandbees;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_19.bearsandbees.BearsAndBees",
			"MULTI_NUMBER",
			"2/__4 4/__1 2/__1 3/__2 3/__3 4/__3 3/__1 2/__1 3/__2 3/__;;8 18/__3 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
