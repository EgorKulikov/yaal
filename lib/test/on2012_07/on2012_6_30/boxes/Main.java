package on2012_07.on2012_6_30.boxes;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_30.boxes.Boxes",
			"MULTI_NUMBER",
			"1/__8 0 3 4 5 7 2 2 0;;11;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
