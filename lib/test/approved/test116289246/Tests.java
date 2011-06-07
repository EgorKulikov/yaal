package approved.test116289246;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("2\n2 1 5", "2 1\n1 "),
		new Test("6\n1 2 1\n1 3 5\n3 4 2\n3 5 3\n3 6 4", "16 1\n2 "),
		new Test("6\n1 2 1\n2 3 1\n3 4 1\n4 5 1\n5 6 1", "18 1\n3"),
		new Test("6\n1 2 1\n2 3 1\n2 5 1\n4 5 1\n5 6 1", "18 1\n3"),
	};
}
