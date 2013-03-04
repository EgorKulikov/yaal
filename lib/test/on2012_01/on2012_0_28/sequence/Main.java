package on2012_01.on2012_0_28.sequence;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.sequence.Sequence",
			"SINGLE",
			"0;;0;;true::2;;2;;true::3;;2;;true::21;;10;;true::6;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
