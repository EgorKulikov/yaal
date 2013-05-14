package on2012_11.on2012_11_16_Codeforces_Round__150.D___Cubes;



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
		Random random = new Random(239);
		out.printLine(1000, random.nextInt(20001) - 10000, random.nextInt(20001) - 10000);
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++)
				out.printLine(random.nextInt((int) (1e9 + 1)));
		}
		return Collections.singleton(new Test(sw.toString()));
	}
}
