package on2012_06.on2012_5_6.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_6.taska.TaskA",
			"SINGLE",
			"5 4/__####/__#..#/__#..#/__#..#/__####/__;;2/__;;true::5 5/__#####/__#...#/__#####/__#...#/__#####/__;;2/__;;true::3 3/__#../__.../__.../__;;-1;;true::3 5/__##.##/__#####/__##.##;;1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
