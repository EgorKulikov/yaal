package on2013_06.on2013_06_23_June_Cook_Off_2013.Tywins_Tactics;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_06/on2013_06_23_June_Cook_Off_2013/Tywins_Tactics/Tywins Tactics.task"))
			Assert.fail();
	}
}
