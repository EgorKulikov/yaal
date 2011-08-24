package approved.test911889265;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("3 2\n1 2 10\n2 3 20", "1\n1 3 10"),
		new Test("3 3\n1 2 20\n2 3 10\n3 1 5", "0"),
		new Test("4 2\n1 2 60\n3 4 50", "2\n1 2 60\n3 4 50"),
	};
}
