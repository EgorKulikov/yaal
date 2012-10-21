package on2012_08.on2012_7_17.domino;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_17.domino.Domino",
			"SINGLE",
			"3/__2 4/__4 6/__6 2;;6;;true::3/__6 7/__7 7/__6 7;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
