package approved.test34246038;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("5 100\n20 20 20 20 20", "1"),
		new Test("4 2000\n100 200 300 350", "3"),
		new Test("8 840\n105 120 140 168 210 280 420 840", "8"),
	};
}
