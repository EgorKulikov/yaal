package on2012_02.on2012_1_2.restoretherecipe;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_2.restoretherecipe.RestoreTheRecipe",
			"SINGLE",
			"4 4/__1 2 3/__2 3 1/__3 4 -2/__1 4 1;;1 2 -1 -1;;true::5 3/__1 3 4/__4 5 6/__1 5 9 ;;-1;;true::4 4/__1 4 10/__2 4 6/__3 4 3/__4 4 1/__;;4 3 2 1/__;;true::4 4/__1 1 1/__1 2 3/__1 3 6/__1 4 10/__;;1 2 3 4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
