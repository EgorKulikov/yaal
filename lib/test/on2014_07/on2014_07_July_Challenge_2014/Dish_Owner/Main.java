package on2014_07.on2014_07_July_Challenge_2014.Dish_Owner;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_07/on2014_07_July_Challenge_2014/Dish_Owner/Dish Owner.task"))
			Assert.fail();
	}
}
