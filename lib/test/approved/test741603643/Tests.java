package approved.test741603643;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("4\n40 10 30 20\n1 2 1\n2 3 1\n4 2 1", "0 1 3 2"),
		new Test("5\n5 4 3 2 1\n1 2 1\n2 3 1\n2 4 1\n4 5 1", "0 1 4 2 3"),
	};
}
