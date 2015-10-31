package on2015_08.on2015_08_23_August_Cook_Off_2015.Chef_and_the_Cards;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_08/on2015_08_23_August_Cook_Off_2015/Chef_and_the_Cards/Chef and the Cards.task"))
			Assert.fail();
	}
}
