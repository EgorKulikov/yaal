package on2012_03.on2012_2_21.rollingdicedivone;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("long mostLikely(int[] dice)",
			"on2012_03.on2012_2_21.rollingdicedivone.RollingDiceDivOne",
			"6,6,8;;11;;true::10;;1;;true::2,3,4,5;;9;;true::1,10,1;;3;;true::382828264,942663792,291832707,887961277,546603677,545185615,146267547,6938117,167567032,84232402,700781193,452172304,816532384,951089120,448136091,280899512,256093435,39595226,631504901,154772240;;4366828428;;true"))
		{
			Assert.fail();
		}
	}
}
