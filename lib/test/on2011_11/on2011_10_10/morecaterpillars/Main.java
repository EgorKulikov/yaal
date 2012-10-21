package on2011_11.on2011_10_10.morecaterpillars;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_10.morecaterpillars.MoreCaterpillars",
			"SINGLE",
			"2 12/__V5 1 1/__H3 7 10;;###........./__..#........./__###........./__#.........../__###........./__..#........./__###........./__#.........../__###........./__..#...#.###./__......#.#.#./__......###.##;;true::4 14/__H3 3 1/__H3 4 4/__V4 2 5/__V3 8 7;;..#.###......./__..#.#.#......./__..###.##....../__...#.###....../__.###.#.#....../__...###.##...../__.###...###..../__.#.......#..../__.###...###..../__...#...#....../__.###...###..../__.#.......#..../__............../__..............;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
