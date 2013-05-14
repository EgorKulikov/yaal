package on2012_01.on2012_0_31.monsterfarm;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int numMonsters(String[] transforms)",
			"on2012_01.on2012_0_31.monsterfarm.MonsterFarm",
			"1;;1;;true::1 1;;-1;;true::2,3,1;;1;;true::1,3 4,2,2;;1;;true::2 2,3,4 4 4,5,6,7 7 7 7,7;;24;;true::2 3,5 7,2 4,5,6,4,7;;5;;true::2 3 4,3,4,2;;3;;true::1 2 3 4,2,3,4;;-1;;true"))
		{
			Assert.fail();
		}
	}
}
