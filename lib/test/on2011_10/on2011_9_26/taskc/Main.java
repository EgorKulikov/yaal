package on2011_10.on2011_9_26.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_26.taskc.TaskC",
			"SINGLE",
			"8 10/__##########/__#.#...##.#/__#.#..###.#/__#.#.##...#/__#.......##/__#...###..#/__#....T#..#/__##########;;No::8 10/__##########/__#.#...##.#/__#.#..###.#/__#.#.##...#/__#.......##/__#..####..#/__#....T#..#/__##########;;Yes"))
		{
			Assert.fail();
		}
	}
}
