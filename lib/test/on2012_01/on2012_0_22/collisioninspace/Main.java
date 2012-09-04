package on2012_01.on2012_0_22.collisioninspace;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_22.collisioninspace.CollisionInSpace",
			"MULTI_NUMBER",
			"3/__0 0 R/__2/__1 -2 U/__2 2 D/__1 1 U/__1/__1 0 U/__0 0 R/__1/__3 0 L/__;;2.0/__SAFE/__1.5/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
