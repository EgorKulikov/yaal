package on2012_0_14.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_14.taskd.TaskD",
			"SINGLE",
			"2 3 1/__R-G/__RG-/__;;First/__;;true::3 3 2/__G-R/__R-G/__G-R/__;;Second/__;;true::2 3 1/__-R-/__-G-/__;;Draw/__;;true::2 5 2/__-G-R-/__-R-G-/__;;First/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
