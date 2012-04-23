package on2012_3_23.bookshelf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_23.bookshelf.Bookshelf",
			"SINGLE",
			"5 10/__5 7/__9 2/__8 5/__13 2/__3 8;;21;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
