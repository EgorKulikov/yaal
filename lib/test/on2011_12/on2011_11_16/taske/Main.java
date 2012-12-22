package on2011_12.on2011_11_16.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_16.taske.TaskE",
			"SINGLE",
			"Abo/__;;3 1/__;;true::OEIS/__;;3 1/__;;true::auBAAbeelii/__;;9 3/__;;true::AaaBRAaaCAaaDAaaBRAaa/__;;18 4/__;;true::EA/__;;No solution/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
