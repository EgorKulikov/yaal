package on2012_6_23.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_6_23.taske.TaskE",
			"SINGLE",
			"6/__0 1 1 0 4 4/__7/__1 1/__1 2/__2 1/__2 2/__4 1/__5 1/__6 1/__;;0 0 1 0 0 1 1 /__;;true::7/__0 1 1 2 2 3 3/__3/__2 1/__4 2/__4 1/__;;1 2 1/__;;true::10/__10 1 2 3 4 1 6 7 8 0/__5/__5 1/__5 2/__5 3/__5 4/__5 5/__/__;;0 0 0 1 0/__;;true::6/__0 1 2 0 4 5/__5/__3 1/__3 2/__3 3/__3 4/__3 5/__/__;;0 0 0 0 0/__;;true::15/__0 1 1 2 2 3 3 4 4 5 5 6 6 7 7/__8/__15 1/__15 2/__15 3/__15 4/__7 1/__7 2/__7 3/__3 1/__;;1 2 4 0 1 2 0 1/__/__;;true::13/__11 1 1 1 1 1 1 1 1 1 0 11 12/__6/__1 1/__2 2/__3 1/__4 2/__5 1/__13 2;;1 1 8 1 8 9/__;;true::1/__0/__1/__1 1/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
