package on2011_11.on2011_10_19.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_19.taskb.TaskB",
			"SINGLE",
			"4 3 XXX X.X X.X XXX;;1;;true::5 5 XX.XX X...X ..... X...X XX.XX;;1;;true::3 9 ...XXX... .X.....X. ...XXX...;;0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
