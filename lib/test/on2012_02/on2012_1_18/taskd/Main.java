package on2012_02.on2012_1_18.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_18.taskd.TaskD",
			"SINGLE",
			"7/__-1 -1 -1 -1 -1 -1 -1/__abacaba/__;;0/__;;true::7/__1 -1 -1 -1 -1 -1 -1/__abacaba/__;;7/__;;true::7/__1 5 -1 -1 -1 -1 10/__abacaba/__;;16/__;;true::10/__0 -1 100 -1 -1 -1 -1 -1 -1 -1/__eabaffeece/__;;300/__;;true::11/__-1 -1 -1 -1 100 -1 -1 -1 -1 -1 -1/__abbbbbbaaaa/__;;100/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
