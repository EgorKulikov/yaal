package approved.test1132988262;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("5 2\naba\nbabc", "2"),
		new Test("2 2\nab\na", "2"),
		new Test("3 2\naba\nb", "2"),
	};
}
