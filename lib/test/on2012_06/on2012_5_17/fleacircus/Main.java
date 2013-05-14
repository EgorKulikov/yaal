package on2012_06.on2012_5_17.fleacircus;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int countArrangements(String[] afterFourClicks)",
			"on2012_06.on2012_5_17.fleacircus.FleaCircus",
			"1 2 0 3;;1;;true::1 2 0 3;;1;;true::0 1 2;;4;;true::0 1 2 3 5 4;;0;;true::3 6 7 9 8 2 1,0 5 1 0 4;;4;;true::1 0 7 5 6 3 4 2;;48;;true::0 1 2 3;;16;;true"))
		{
			Assert.fail();
		}
	}
}
