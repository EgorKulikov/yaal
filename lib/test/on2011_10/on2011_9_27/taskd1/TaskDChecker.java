package on2011_10.on2011_9_27.taskd1;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;

import java.util.Collection;
import java.util.Collections;

public class TaskDChecker {
	public String check(InputReader input, InputReader expected, InputReader actual) {
		return "";
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringBuilder builder = new StringBuilder();
		builder.append("100000 1000000000000000000\n");
		for (int i = 0; i < 50000; i++)
			builder.append("1 500000000000000000\n");
		for (int i = 0; i < 50000; i++)
			builder.append("500000000000000001 1000000000000000000\n");
		return Collections.singleton(new Test(builder.toString(), "0", 0));
	}
}
