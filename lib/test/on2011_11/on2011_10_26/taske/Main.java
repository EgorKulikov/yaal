package on2011_11.on2011_10_26.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taske.TaskE",
			"MULTI_NUMBER",
			"3 /__1 123 /__2 279134399742 /__3 987/__;;1 132/__2 279134423799/__3 BIGGEST/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
