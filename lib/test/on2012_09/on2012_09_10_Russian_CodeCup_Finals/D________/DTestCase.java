package on2012_09.on2012_09_10_Russian_CodeCup_Finals.D________;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class DTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(100000, 1000, 1000);
		Random random = new Random(239);
		for (int i = 0; i < 1000; i++)
			out.printLine(1, random.nextInt(3) + 1);
		return Collections.singleton(new Test(sw.toString()));
	}
}
