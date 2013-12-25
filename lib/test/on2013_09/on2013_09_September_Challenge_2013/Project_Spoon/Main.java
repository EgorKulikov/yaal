package on2013_09.on2013_09_September_Challenge_2013.Project_Spoon;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_09/on2013_09_September_Challenge_2013/Project_Spoon/Project Spoon.task"))
			Assert.fail();
	}
}
