package on2012_06.on2012_5_16.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_16.taskb.TaskB",
			"SINGLE",
			"5 4/__##.#/__##S#/__#..#/__#.##/__#..#/__;;Yes/__;;true::5 4/__##.#/__##S#/__#..#/__..#./__#.##/__;;No/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
