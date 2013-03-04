package on2012_06.on2012_5_16.taska;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(permutation);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < count; i++)
			setSystem.join(i, permutation[i]);
		setSystem.join(0, 1);
		for (int i = 0; i < queryCount; i++) {
			if (setSystem.get(in.readInt() - 1) == setSystem.get(in.readInt() - 1))
				out.printLine("Yes");
			else
				out.printLine("No");
		}
	}
}
