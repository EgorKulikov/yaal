package on2012_04.on2012_3_4.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_4.taskc.TaskC",
			"SINGLE",
			"1 10/__3 5/__;;8/__;;true::2 1/__3 5/__4 5/__;;8 19/__;;true::5 4/__3 5/__4 5/__5 5/__6 5/__7 1/__;;11 11 11 11 20/__;;true::20 4/__28 13/__31 13/__35 6/__36 4/__52 6/__53 4/__83 2/__84 4/__87 1/__93 6/__108 4/__113 6/__116 1/__125 2/__130 2/__136 13/__162 2/__166 4/__184 1/__192 2/__;;51 51 43 40 93 89 86 89 114 121 118 121 137 139 139 152 195 199 193 195/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
