package on2011_11.on2011_10_25.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_25.taske.TaskE",
			"SINGLE",
			"8 4/__4 3/__4 8/__6 5/__1 6/__;;0 3 0 1 0 0 0 0 0 ;;true::10 3/__1 1/__1 2/__1 3/__;;0 2 1 0 0 0 0 0 0 ;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
