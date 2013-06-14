package on2011_12.on2011_11_18.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_18.taskb.TaskB",
			"SINGLE",
			"235217 3 A 107382 C 18059 B 43265;;A 9 B 4 C 1;;true::245143 4 F 14845 A 104516 B 52652 C 14161;;A 8 B 4 C 1 F 1;;true::206278 5 D 44687 A 68188 C 7008 B 48377 G 9665;;A 6 B 4 D 4;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
