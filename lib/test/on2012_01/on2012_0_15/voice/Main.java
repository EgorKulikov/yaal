package on2012_01.on2012_0_15.voice;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_15.voice.Voice",
			"MULTI_NUMBER",
			"3/__this is unacceptable/__unacceptable as shit/__come to me bander logs/__bender logs me/__to be or not to be/__be or;;3/__1/__0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
