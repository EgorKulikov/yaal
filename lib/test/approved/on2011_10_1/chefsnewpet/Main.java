package approved.on2011_10_1.chefsnewpet;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_1.chefsnewpet.ChefsNewPet",
			"MULTI_NUMBER",
			"3/__10/__1 1/__13/__3 1 2 8/__15/__5 1 2 3 4 5;;1/__415/__13624;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
