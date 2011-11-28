package on2011_10_26.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_26.taska.TaskA",
			"MULTI_NUMBER",
			"4 /__1 1 2 3 4 5 6 7 8 9 1000/__2 338 304 619 95 343 496 489 116 98 127/__3 931 240 986 894 826 640 965 833 136 138/__4 940 955 364 188 133 254 501 122 768 408/__;;1 8/__2 489/__3 931/__4 768/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
