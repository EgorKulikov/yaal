package on2013_02.on2013_02_22_Exhibit_2013.Dual_core_processor_and_the_scheduler;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DualCoreProcessorAndTheScheduler {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] times = IOUtils.readIntArray(in, count);
		int sum = (int) ArrayUtils.sumArray(times);
		boolean[] can = new boolean[sum / 2 + 1];
		can[0] = true;
		for (int i : times) {
			for (int j = can.length - i - 1; j >= 0; j--) {
				if (can[j])
					can[j + i] = true;
			}
		}
		for (int i = can.length - 1; i >= 0; i--) {
			if (can[i]) {
				out.printLine(sum - 2 * i);
				return;
			}
		}
    }
}
