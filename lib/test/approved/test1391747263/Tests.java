package approved.test1391747263;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("6 8\n1 2\n2 3\n1 3\n4 5\n4 6\n5 6\n2 4\n3 5", "1 2\n2 3\n3 1\n4 5\n5 6\n6 4\n4 2\n3 5"),
		new Test("6 7\n1 2\n2 3\n1 3\n4 5\n4 6\n5 6\n2 4", "0"),
	};
}
