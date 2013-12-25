package on2011_10.on2011_9_29.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taske.TaskE",
			"SINGLE",
			"6/__english/__unknown/__unknown/__unknown/__german/__unknown;;2 3 6::4/__english/__french/__unknown/__english;;Igor is wrong.::3/__zulu/__zulu/__zulu;;1"))
		{
			Assert.fail();
		}
	}
}
