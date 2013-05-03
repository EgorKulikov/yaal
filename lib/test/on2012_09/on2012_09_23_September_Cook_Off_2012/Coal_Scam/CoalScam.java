package on2012_09.on2012_09_23_September_Cook_Off_2012.Coal_Scam;



import net.egork.misc.ArrayUtils;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoalScam {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int cheapEdgesCount = in.readInt();
		int expensiveEdgeCount = in.readInt();
		int[] cheapFrom = new int[cheapEdgesCount];
		int[] cheapTo = new int[cheapEdgesCount];
		int[] cheapPrice = new int[cheapEdgesCount];
		int[] expensiveFrom = new int[expensiveEdgeCount];
		int[] expensiveTo = new int[expensiveEdgeCount];
		int[] expensivePrice = new int[expensiveEdgeCount];
		IOUtils.readIntArrays(in, cheapFrom, cheapTo, cheapPrice);
		IOUtils.readIntArrays(in, expensiveFrom, expensiveTo, expensivePrice);
		int[] cheapOrder = ArrayUtils.order(cheapPrice);
		int[] expensiveOrder = ArrayUtils.order(expensivePrice);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		long sumExpensive = 0;
		long sum = 0;
		for (int ii = expensiveEdgeCount - 1; ii >= 0; ii--) {
			int i = expensiveOrder[ii];
			if (setSystem.join(expensiveFrom[i], expensiveTo[i])) {
				sumExpensive += expensivePrice[i];
				sum += expensivePrice[i];
			}
		}
		for (int i : cheapOrder) {
			if (setSystem.join(cheapFrom[i], cheapTo[i]))
				sum += cheapPrice[i];
		}
		if (setSystem.getSetCount() != 1) {
			out.printLine("Impossible");
			return;
		}
		out.printLine(sumExpensive, sum);
	}
}
