package on2015_05.on2015_05_08_May_Challenge_2015.Chef_and_new_recipe;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_05/on2015_05_08_May_Challenge_2015/Chef_and_new_recipe/Chef and new recipe.task"))
			Assert.fail();
	}
}
