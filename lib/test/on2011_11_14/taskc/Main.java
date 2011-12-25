package on2011_11_14.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_14.taskc.TaskC",
			"SINGLE",
			"3 2/__petr/__lexa/__sergey/__PROBA/__PROBB/__4/__lexa-PROBA-100/__???g??-?????-0/__?e??-PROBA-70/__?e??-PROB?-60/__2/__3 2;;160;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
