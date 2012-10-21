package on2012_06.on2012_5_16.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_16.taske.TaskE",
			"SINGLE",
			"3/__ab/__cd/__abcd;;Good vocabulary!;;true::3/__aba/__ab/__ac;;abab;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
