package on2012_02.on2012_1_22.dengklekmakingchains;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int maxBeauty(String[] chains)",
			"on2012_02.on2012_1_22.dengklekmakingchains.DengklekMakingChains",
			".15,7..,402,..3;;19;;true::..1,7..,567,24.,8..,234;;36;;true::...,...;;0;;true::16.,9.8,.24,52.,3.1,532,4.4,111;;28;;true::..1,3..,2..,.7.;;7;;true::412,..7,.58,7.8,32.,6..,351,3.9,985,...,.46;;58;;true"))
		{
			Assert.fail();
		}
	}
}
