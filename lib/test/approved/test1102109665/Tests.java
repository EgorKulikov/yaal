package approved.test1102109665;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("7\n10 20 15 10 30 5 1", "6"),
		new Test("3\n15 15 10", "1"),
		new Test("3\n10 15 20", "0"),
	};
}
