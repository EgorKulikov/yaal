package on2014_06.on2014_06_28_Addepar_Hackathon.ConsistentSalaries;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ConsistentSalaries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count - 1);
		MiscUtils.decreaseByOne(parent);
		int[] salary = IOUtils.readIntArray(in, count);
		int[] delta = new int[count];
		int bad = 0;
		for (int i = 1; i < count; i++) {
			delta[i] = salary[parent[i - 1]] - salary[i];
			if (delta[i] < 0) {
				bad++;
			}
		}
		delta[0] = Integer.MAX_VALUE / 2;
		for (int i = 0; i < queryCount; i++) {
			int index = in.readInt() - 1;
			int change = in.readInt();
			if (delta[index] < 0) {
				bad--;
			}
			delta[index] -= change;
			if (delta[index] < 0) {
				bad++;
			}
			if (bad == 0) {
				out.printLine("GOOD");
			} else {
				out.printLine("BAD");
			}
		}
    }
}
