package on2013_05.on2013_05_19_May_Cook_off_2013.Sequence_Transmission;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SequenceTransmission {
	static final long MOD = 1000000009;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int bit = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int cycle = 1 << bit;
		LongIntervalTree tree = new SumIntervalTree(cycle);
		tree.update(0, 0, 1);
		int sum = 0;
		int delta = (1 << (bit - 1)) - 1;
		long result = 0;
		for (int i : numbers) {
			sum += i;
			if (sum >= cycle)
				sum -= cycle;
			if (sum >= delta)
				result = tree.query(sum - delta, sum);
			else
				result = tree.query(0, sum) + tree.query(sum - delta + cycle, cycle - 1);
			result %= MOD;
			tree.update(sum, sum, result);
		}
		out.printLine(result);
    }
}
