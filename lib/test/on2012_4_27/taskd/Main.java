package on2012_4_27.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_4_27.taskd.TaskD",
			"SINGLE",
			"2/__uu/__u/__5/__.uu..uu/__.uu...u/__...uu.u/__.u.u.uu/__uuu.uu.;;2/__1 2;;true::1/__a/__1/__a./__;;2/__0 1/__;;true::5/__abc/__bc/__c/__ab/__a/__2/__bc.a/__bc.a/__;;4/__0 1 2 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
