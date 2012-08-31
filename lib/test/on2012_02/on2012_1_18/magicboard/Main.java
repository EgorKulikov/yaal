package on2012_02.on2012_1_18.magicboard;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("String ableToUnlock(String[] board)",
			"on2012_02.on2012_1_18.magicboard.MagicBoard",
			"##,.#;;YES;;true::#.,.#;;NO;;true::##,##,##;;YES;;true::###,###,###;;NO;;true::###,..#,###,#..,###;;NO;;true::................,.######..######.,.######..######.,.##......##..##.,.##......##..##.,.######..######.,.######..######.,.....##..##..##.,.....##..##..##.,.######..######.,.######..######.,................;;YES;;true::#;;YES;;true::#,#;;NO;;true"))
		{
			Assert.fail();
		}
	}
}
