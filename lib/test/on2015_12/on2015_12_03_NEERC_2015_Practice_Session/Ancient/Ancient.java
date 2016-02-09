package on2015_12.on2015_12_03_NEERC_2015_Practice_Session.Ancient;



import net.egork.generated.collections.list.CharArray;
import net.egork.generated.collections.list.IntArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Ancient {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		if (new IntArray(new CharArray(first).qty(256)).sort().equals(new IntArray(new CharArray(second).qty(256)).sort()))
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
