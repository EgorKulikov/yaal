package on2012_04.on2012_3_22.topbatsmen;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_22.topbatsmen.TopBatsmen",
			"MULTI_NUMBER",
			"2/__1 2 3 4 5 6 7 8 9 10 11/__3/__2 5 1 2 4 1 6 5 2 2 1/__6/__;;1/__6/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
