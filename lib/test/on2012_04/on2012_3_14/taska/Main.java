package on2012_04.on2012_3_14.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_14.taska.TaskA",
			"MULTI_NUMBER",
			"3/__ejp mysljylc kd kxveddknmc re jsicpdrysi/__rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd/__de kr kd eoya kw aej tysr re ujdr lkgc jv/__;;Case #1/: our language is impossible to understand/__Case #2/: there are twenty six factorial possibilities/__Case #3/: so it is okay if you want to just give up/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
