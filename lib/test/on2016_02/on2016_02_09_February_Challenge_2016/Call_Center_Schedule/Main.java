package on2016_02.on2016_02_09_February_Challenge_2016.Call_Center_Schedule;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2016_02/on2016_02_09_February_Challenge_2016/Call_Center_Schedule/Call Center Schedule.task"))
			Assert.fail();
	}
}
