package on2016_05.on2016_05_27_SnackDown_Online_Qualifier_2016.Kitchen_Timetable;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2016_05/on2016_05_27_SnackDown_Online_Qualifier_2016/Kitchen_Timetable/Kitchen Timetable.task"))
			Assert.fail();
	}
}
