package on2012_2_31.martianfootballtournament;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_31.martianfootballtournament.MartianFootballTournament",
			"SINGLE",
			"2 /__6 -4;;10;;true::3 /__3 5 7;;6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
