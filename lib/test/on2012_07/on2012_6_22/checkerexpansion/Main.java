package on2012_07.on2012_6_22.checkerexpansion;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("String[] resultAfter(long t, long x0, long y0, int w, int h)",
			"on2012_07.on2012_6_22.checkerexpansion.CheckerExpansion",
			"1;;0;;0;;4;;4;;....,....,....,A...;;true::5;;4;;1;;3;;4;;A..,...,B..,.B.;;true::1024;;1525;;512;;20;;2;;B...B...B...........,.B.A.B.A.B..........;;true::53;;85;;6;;5;;14;;.....,.....,B....,.B.A.,.....,.....,.....,.....,.....,.....,B....,.B...,..B..,.A.B.;;true"))
		{
			Assert.fail();
		}
	}
}
