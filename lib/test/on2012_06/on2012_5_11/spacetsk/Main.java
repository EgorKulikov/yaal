package on2012_06.on2012_5_11.spacetsk;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int countsets(int L, int H, int K)",
			"on2012_06.on2012_5_11.spacetsk.Spacetsk",
			"1;;1;;2;;4;;true::1;;1;;1;;4;;true::2;;2;;1;;9;;true::2;;2;;2;;23;;true::5;;5;;3;;202;;true::561;;394;;20;;786097180;;true"))
		{
			Assert.fail();
		}
	}
}
