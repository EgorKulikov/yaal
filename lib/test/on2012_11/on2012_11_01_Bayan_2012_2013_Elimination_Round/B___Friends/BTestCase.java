package on2012_11.on2012_11_01_Bayan_2012_2013_Elimination_Round.B___Friends;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class BTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(50000, 12500 * 49999);
		Random random = new Random(239);
		for (int i = 0; i < 50000; i++)
			out.printLine(random.nextInt((int) (1e9 + 1)));
		return Collections.singleton(new Test(sw.toString()));
//		return Collections.emptyList();
	}
}
