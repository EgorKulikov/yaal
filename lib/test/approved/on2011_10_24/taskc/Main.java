package approved.on2011_10_24.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_24.taskc.TaskC",
			"MULTI_EOF",
			"C E G /__C E   F# /__G	C	E /__C Eb G /__c# a f# /__   f g#      C/__;;C E G is a C Major chord./__C E F# is unrecognized./__G C E is a C Major chord./__C Eb G is a C Minor chord./__c# a f# is a F# Minor chord./__f g# C is a F Minor chord./__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
