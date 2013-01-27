package on2012_01.on2012_0_22.makxsum;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_22.makxsum.MakxSum",
			"MULTI_NUMBER",
			"3/__3 1 2 3/__10 20 30/__3 3 4 6/__10 20 30/__4 2 6 10/__20 -15 10 -15/__;;60 50 30/__30 30 10/__15 -5 -20/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
