package approved.test1279216111;

import net.egork.utils.test.Test;
public class Tests {
	public static final Test[] TESTS = {
		new Test("5 6\nhtml text/html\nhtm text/html\npng image/png\nsvg image/svg+xml\ntxt text/plain\nindex.html\nthis.file.has.lots.of.dots.txt\nnodotsatall\nvirus.exe\ndont.let.the.png.fool.you\ncase.matters.TXT", "text/html\ntext/plain\nunknown\nunknown\nunknown\nunknown"),
	};
}
