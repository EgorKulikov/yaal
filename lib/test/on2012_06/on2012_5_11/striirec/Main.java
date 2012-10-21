package on2012_06.on2012_5_11.striirec;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("String recovstr(int n, int minInv, String minStr)",
			"on2012_06.on2012_5_11.striirec.StrIIRec",
			"2;;1;;ab;;ba;;true::9;;1;;efcdgab;;efcdgabhi;;true::11;;55;;debgikjfc;;kjihgfedcba;;true::15;;0;;e;;eabcdfghijklmno;;true::9;;20;;fcdebiha;;fcdehigba;;true::20;;190;;abcdefghijklmnopqrst;;tsrqponmlkjihgfedcba;;true"))
		{
			Assert.fail();
		}
	}
}
