package on2012_04.on2012_3_21.taskc;



import net.egork.collections.CollectionUtils;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int friendshipsCount = in.readInt();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < friendshipsCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			setSystem.join(first, second);
		}
		int[] result = new int[count];
		for (int i = 0; i < count; i++)
			result[setSystem.get(i)]++;
		int enemiesCount = in.readInt();
		for (int i = 0; i < enemiesCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			if (setSystem.get(first) == setSystem.get(second))
				result[setSystem.get(first)] = 0;
		}
		out.printLine(CollectionUtils.maxElement(Array.wrap(result)));
	}
}
