package on2012_01.on2012_0_28.wolf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.wolf.Wolf",
			"SINGLE",
			"Farmer Cabbage Goat/__Wolf;;Cabbage/__Self/__Goat;;true::Farmer/__Goat Cabbage Wolf;;Impossible/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
