package on2011_10_28.task1712;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_28.task1712.Task1712",
			"SINGLE",
			"..../__X..X/__.X../__...X/__Pwoo/__Khaa/__smrs/__odbk/__;;KamkohobPassword;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
