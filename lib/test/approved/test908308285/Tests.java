package approved.test908308285;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("3 2001 2\n1 2\n2 3", "1 2 3\n"),
		new Test("7 2020 6\n1 2\n1 3\n2 4\n2 5\n3 6\n3 7", "1 2 3 7 4 6 5"),
		new Test("10 3630801 0", "The times have changed"),
		new Test("3 2001 3\n1 2\n2 3\n3 1", "The times have changed"),
	};
}
