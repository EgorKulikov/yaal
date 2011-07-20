package approved.test419199440;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("4 3\n1 2\n2 3\n1 3", "1"),
		new Test("5 4\n1 2\n3 4\n4 5\n3 5", "-1\n"),
		new Test("4 3\n1 2\n2 3\n3 4\n", "0\n"),
		new Test("4 1\n1 1\n", "3\n"),
		new Test("7 4\n1 2\n3 4\n5 6\n6 7\n", "1\n"),
		new Test("20 1\n5 15\n", "2\n"),
	};
}
