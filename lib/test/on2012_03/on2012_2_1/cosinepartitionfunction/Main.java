package on2012_03.on2012_2_1.cosinepartitionfunction;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.cosinepartitionfunction.CosinePartitionFunction",
			"MULTI_NUMBER",
			"4/__1 3 1.0/__2 3 0/__30 1 0.1/__1 1 0.5/__;;0.5403023059/__4.0000000000/__29.8501249583/__0.8775825619/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
