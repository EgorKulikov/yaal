package on2012_08.on2012_7_19.rebuildingbyteland;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_19.rebuildingbyteland.RebuildingByteland",
			"MULTI_NUMBER",
			"2/__3 3/__1 2/__2 3/__3 1/__2/__1 2/__5 7/__1 3/__1 4/__3 4/__2 4/__2 5/__4 5/__1 2/__2/__2 4/__;;YES/__NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
