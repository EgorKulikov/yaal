package on2012_02.on2012_1_1.wordcouting;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_1.wordcouting.WordCouting",
			"MULTI_NUMBER",
			"4/__ab/__aa/__aA/__AAbaz/__;;2/__1/__2/__60/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
