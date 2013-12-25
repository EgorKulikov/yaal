package on2012_05.on2012_4_30.splittingfoxes;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int sum(long n, int S, int L, int R)",
			"on2012_05.on2012_4_30.splittingfoxes.SplittingFoxes",
			"58;;2;;0;;0;;0;;true::3;;1;;1;;0;;1;;true::5;;1;;3;;2;;34;;true::5;;1;;2;;3;;999999973;;true::123456789;;987654321;;544;;544;;0;;true::65536;;1024;;512;;4096;;371473914;;true"))
		{
			Assert.fail();
		}
	}
}
