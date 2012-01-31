package on2011_11_18.photo;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_18.photo.Photo",
			"SINGLE",
			"5/__10 /__20 /__30 /__40 /__50/__20/__10/__30/__40/__50/__30/__10/__20/__40/__50/__40/__10/__20/__30/__50/__50/__10/__20/__30/__40;;10/__20/__30/__40/__50;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
