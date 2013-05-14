package on2013_03.on2013_03_19_CodeZilla_2013.ZooZooland_Water_Shortage;


import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ZooZoolandWaterShortage {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, length);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		int[] order = ArrayUtils.order(length);
		long answer = 0;
		for (int i : order) {
			if (setSystem.join(from[i], to[i]))
				answer += length[i];
		}
		out.printLine(answer);
    }
}
