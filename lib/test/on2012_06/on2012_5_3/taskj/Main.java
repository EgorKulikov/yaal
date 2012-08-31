package on2012_06.on2012_5_3.taskj;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_3.taskj.TaskJ",
			"MULTI_EOF",
			"1/__2/__3/__4/__5/__6/__7/__8/__9/__10/__100/__10000/__10000000/__100000000000/__10000000000000000/__0;;0/__1/__2/__3/__6/__11/__20/__37/__68/__125/__616688122/__335363379/__272924536/__48407255/__163452242;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
