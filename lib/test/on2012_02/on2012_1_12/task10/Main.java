package on2012_02.on2012_1_12.task10;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task10.Task10",
			"SINGLE",
			"16/__1.0 0.0/__2.0 0.0/__3.0 2.0/__4.0 0.0/__5.0 0.0/__1.0 1.1/__4.0 5.0/__3.0 -1.0/__0.0 0.0/__0.5 1.75/__0.75 3.0/__1.5 4.5/__2.75 3.5/__3.0 2.5/__1.75 2.75/__3.0 1.0/__4/__1.5 1.5/__2.0 1.0/__3.5 1.5/__2.25 3.0;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
