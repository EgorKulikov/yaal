package on2011_11.on2011_10_15.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_15.taska.TaskA",
			"SINGLE",
			".......A/__......../__......../__......../__......../__......../__......../__M......./__;;WIN/__;;true::.......A/__......../__......../__......../__......../__......../__SS....../__M......./__;;LOSE/__;;true::.......A/__......../__......../__......../__......../__.S....../__S......./__MS....../__;;LOSE/__;;true::......SA/__.....S../__....S.../__...S..../__..S...../__.S....../__S......./__M......./__;;WIN;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
