package approved.test2125733104;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("1\n1 1", "1"),
		new Test("2\n1 1\n2 2", "2"),
		new Test("3\n1 0\n2 0\n0 1", "2"),
	};
}
