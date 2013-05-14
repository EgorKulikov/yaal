package on2012_01.on2012_0_15.kingsort;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("String[] getSortedList(String[] kings)",
			"on2012_01.on2012_0_15.kingsort.KingSort",
			"Louis IX,Louis VIII;;Louis VIII,Louis IX;;true::Louis IX,Philippe II;;Louis IX,Philippe II;;true::Richard III,Richard I,Richard II;;Richard I,Richard II,Richard III;;true::John X,John I,John L,John V;;John I,John V,John X,John L;;true::Philippe VI,Jean II,Charles V,Charles VI,Charles VII,Louis XI;;Charles V,Charles VI,Charles VII,Jean II,Louis XI,Philippe VI;;true::Philippe II,Philip II;;Philip II,Philippe II;;true"))
		{
			Assert.fail();
		}
	}
}
