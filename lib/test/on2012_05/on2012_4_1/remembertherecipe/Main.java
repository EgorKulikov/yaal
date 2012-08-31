package on2012_05.on2012_4_1.remembertherecipe;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_1.remembertherecipe.RememberTheRecipe",
			"SINGLE",
			"4/__flour-with-eggs 100/__chicken-ham -10/__flour-without-eggs 200/__fish-with-pepper 1100/__6/__f/__flour-with/__flour-with-/__c/__fl/__chik/__;;fish-with-pepper/__flour-without-eggs/__flour-with-eggs/__chicken-ham/__flour-without-eggs/__NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
