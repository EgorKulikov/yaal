package on2012_2_31.pleasingthemartianking;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_31.pleasingthemartianking.PleasingTheMartianKing",
			"SINGLE",
			"2 1 /__-4 -4 ;;0;;true::2 0 /__-2 5 ;;-3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
