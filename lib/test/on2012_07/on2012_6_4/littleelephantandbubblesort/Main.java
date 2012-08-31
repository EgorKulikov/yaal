package on2012_07.on2012_6_4.littleelephantandbubblesort;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_4.littleelephantandbubblesort.LittleElephantAndBubbleSort",
			"MULTI_NUMBER",
			"2/__2 7/__4 7/__50 50/__4 7/__5 6 1 7/__25 74 47 99/__;;0.2500/__1.6049/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
