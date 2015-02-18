package on2014_11.on2014_11_02_OpenCup_Siberia_GP.Task2;





import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int hornCount = in.readInt();
		int hoofCount = in.readInt();
		int[] hornCost = new int[hornCount];
		int[] hornQty = new int[hornCount];
		IOUtils.readIntArrays(in, hornCost, hornQty);
		int[] hoofCost = new int[hoofCount];
		int[] hoofQty = new int[hoofCount];
		IOUtils.readIntArrays(in, hoofCost, hoofQty);
		Room first = new Room(hornCost, hornQty);
		Room second = new Room(hoofCost, hoofQty);
		long totalFirst = 0;
		long totalSecond = 0;
		long firstMore = 0;
		long equal = 0;
		long secondMore = 0;
		int firstAt = 0;
		int secondAt = 0;
		while (firstAt < first.cost.length && secondAt < second.cost.length) {
			if (first.cost[firstAt] < second.cost[secondAt]) {
				firstMore += totalSecond * first.qty[firstAt];
				totalFirst += first.qty[firstAt++];
			} else if (first.cost[firstAt] > second.cost[secondAt]) {
				secondMore += totalFirst * second.qty[secondAt];
				totalSecond += second.qty[secondAt++];
			} else {
				firstMore += totalSecond * first.qty[firstAt];
				secondMore += totalFirst * second.qty[secondAt];
				equal += first.qty[firstAt] * second.qty[secondAt];
				totalFirst += first.qty[firstAt++];
				totalSecond += second.qty[secondAt++];
			}
		}
		while (firstAt < first.cost.length) {
			firstMore += totalSecond * first.qty[firstAt++];
		}
		while (secondAt < second.cost.length) {
			secondMore += totalFirst * second.qty[secondAt++];
		}
		out.printLine(firstMore, equal, secondMore);
    }

	static class Room {
		int[] cost;
		long[] qty;


		Room(int[] cost, int[] qty) {
			ArrayUtils.orderBy(cost, qty);
			this.cost = ArrayUtils.unique(cost);
			this.qty = new long[this.cost.length];
			int at = 0;
			int[] cost1 = this.cost;
			for (int i1 = 0; i1 < cost1.length; i1++) {
				int i = cost1[i1];
				while (at < qty.length && i == cost[at]) {
					this.qty[i1] += qty[at++];
				}
			}
		}
	}
}
