package on2012_08.on2012_7_17.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_17.taske.TaskE",
			"MULTI_NUMBER",
			"3/__5 12/__8 6 7 10 9/__10 1000/__101 101 101 101 101 101 101 101 110/__90/__3 100/__32 33 34;;26/__1/__0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
