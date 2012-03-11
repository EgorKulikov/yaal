package on2012_2_1.spooninmatrix;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_1.spooninmatrix.SpoonInMatrix",
			"MULTI_NUMBER",
			"3/__3 6/__abDefb/__bSpoon/__NIKHil/__6 6/__aaaaaa/__ssssss/__xuisdP/__oooooo/__ioowoo/__bdylan/__6 5/__bdfhj/__cacac/__opqrs/__ddddd/__india/__yucky/__;;There is a spoon!/__There is a spoon!/__There is indeed no spoon!/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
