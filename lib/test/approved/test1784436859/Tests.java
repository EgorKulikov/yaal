package approved.test1784436859;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("4 3\n1 3 5", "3 1 3 2"),
		new Test("10 2\n5 5", "2 1 2 1 2 1 2 1 2 1"),
		new Test("10 3\n1 10 3", "-1"),
		new Test("10 3\n3 3 3\n", "-1\n"),
	};
}
