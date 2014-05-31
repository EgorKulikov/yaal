package on2014_05.on2014_05_26_Learneroo_Challenge.ThePrincesPath;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_05/on2014_05_26_Learneroo_Challenge/ThePrincesPath/ThePrincesPath.task"))
			Assert.fail();
	}
}
