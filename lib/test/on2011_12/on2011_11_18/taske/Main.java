package on2011_12.on2011_11_18.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_18.taske.TaskE",
			"SINGLE",
			"2 3 5 3 1 p 1 5 u 2 u 1;;8 5;;true::5 5 4 2 1 6 1 7 1 3 4 u 3 p 1 -1 u 3 p 4 5 u 5;;6 5 7;;true::6 7 5 4 1 3 2 7 3 2 3 3 5 p 3 2 p 2 4 u 3 u 6 p 5 -2 u 6 u 1;;7 9 7 5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
