package on2011_11.on2011_10_24.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_24.taskd.TaskD",
			"MULTI_NUMBER",
			"3/__BAPC/__BAPC/__AZA/__AZAZAZA/__VERDI/__AVERDXIVYERDIAN/__;;1/__3/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
