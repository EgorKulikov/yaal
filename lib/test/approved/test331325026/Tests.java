package approved.test331325026;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("1\n3 1 2 2 3", "2"),
		new Test("2\n3 1 2 1 3\n4 1 2 2 3 2 4", "4"),
		new Test("2\n5 1 2 2 3 3 4 3 5\n7 3 4 1 2 2 4 4 6 2 7 6 5", "7"),
	};
}
