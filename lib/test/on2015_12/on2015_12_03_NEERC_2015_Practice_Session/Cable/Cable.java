package on2015_12.on2015_12_03_NEERC_2015_Practice_Session.Cable;



import net.egork.generated.collections.IntStream;
import net.egork.generated.collections.function.DoubleToIntFunction;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.DoubleArrayList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Cable {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		IntStream lengths = new DoubleArrayList(IOUtils.readDoubleArray(in, n)).map((DoubleToIntFunction) x -> (int)Math.round(x * 100)).compute();
		int left = 0;
		int right = 10000000;
		while (left < right) {
			int middle = (left + right + 1) >> 1;
			long total = (int) lengths.map((IntToIntFunction) x -> x / middle).sum();
			if (total >= k)
				left = middle;
			else
				right = middle - 1;
		}
		out.printFormat("%.2f\n", left / 100d);
	}
}
