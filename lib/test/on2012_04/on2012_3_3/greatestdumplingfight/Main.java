package on2012_04.on2012_3_3.greatestdumplingfight;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_3.greatestdumplingfight.GreatestDumplingFight",
			"MULTI_NUMBER",
			"3/__2 4 3 6 7/__1 2 4 5 1/__10 12 3 9 16/__;;3/__3/__5/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
