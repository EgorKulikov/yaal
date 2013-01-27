package on2012_06.on2012_5_3.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_3.taskg.TaskG",
			"MULTI_NUMBER",
			"5/__colombia/__abcdba/__neversayeven/__neveroddoreven/__listentothesilence;;c/__ba/__even/__neveroddoreven/__sil;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
