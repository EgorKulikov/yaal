package on2011_11.on2011_10_28.task1837;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.task1837.Task1837",
			"SINGLE",
			"7/__Isenbaev Oparin Toropov/__Ayzenshteyn Oparin Samsonov/__Ayzenshteyn Chevdar Samsonov/__Fominykh Isenbaev Oparin/__Dublennykh Fominykh Ivankov/__Burmistrov Dublennykh Kurpilyanskiy/__Cormen Leiserson Rivest/__;;Ayzenshteyn 2/__Burmistrov 3/__Chevdar 3/__Cormen undefined/__Dublennykh 2/__Fominykh 1/__Isenbaev 0/__Ivankov 2/__Kurpilyanskiy 3/__Leiserson undefined/__Oparin 1/__Rivest undefined/__Samsonov 2/__Toropov 1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
