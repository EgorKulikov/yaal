package on2012_03.on2012_2_12.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_12.taske.TaskE",
			"MULTI_NUMBER",
			"4/__11/__239/__401/__9001/__;;4/__28/__61/__2834/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
