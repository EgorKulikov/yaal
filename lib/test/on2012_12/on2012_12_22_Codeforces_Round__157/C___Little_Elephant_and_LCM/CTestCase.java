package on2012_12.on2012_12_22_Codeforces_Round__157.C___Little_Elephant_and_LCM;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;

public class CTestCase implements TestProvider {
	public Collection<Test> createTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		for (int i = 0; i <= 100000; i++)
			out.printLine(100000);
		return Collections.singleton(new Test(sw.toString()));
	}
}
