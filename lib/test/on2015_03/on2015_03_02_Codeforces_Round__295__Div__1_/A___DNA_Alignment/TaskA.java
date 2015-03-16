package on2015_03.on2015_03_02_Codeforces_Round__295__Div__1_.A___DNA_Alignment;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] sequence = IOUtils.readCharArray(in, length);
		int[] qty = new int[256];
		for (char c : sequence) {
			qty[c]++;
		}
		out.printLine(IntegerUtils.power(ArrayUtils.count(qty, ArrayUtils.maxElement(qty)), length, (long) (1e9 + 7)));
    }
}
