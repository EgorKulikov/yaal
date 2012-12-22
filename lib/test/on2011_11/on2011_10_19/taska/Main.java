package on2011_11.on2011_10_19.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_19.taska.TaskA",
			"SINGLE",
			"20 30 50 48 33 66 0 64;;261/__3 4 5 6 8;;true::20 0 50 80 77 110 56 48;;373 3 4 5 6 7;;true::20 30 50 80 110 11 0 85;;355 2 3 4 5 8;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
