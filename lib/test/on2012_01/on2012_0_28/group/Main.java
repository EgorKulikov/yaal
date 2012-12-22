package on2012_01.on2012_0_28.group;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.group.Group",
			"SINGLE",
			"6/__1 2 3 4 5 6/__2 1 1 1 1 1/__3 1 1 1 1 1/__4 1 1 1 1 1/__5 1 1 1 1 1/__6 1 1 1 1 1;;No;;true::2/__1 3/__3 1;;No;;true::2/__2 1/__1 2;;Yes;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
