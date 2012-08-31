package on2011_11.on2011_10_29.task1756;



import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1756 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int dayCount = in.readInt();
		int newDayCount = in.readInt();
		int workAmount = count * dayCount;
		int cap = (workAmount + newDayCount - 1) / newDayCount;
		int[] answer = new int[newDayCount];
		for (int i = newDayCount - 1; i >= 0; i--) {
			answer[i] = Math.min(cap, workAmount);
			workAmount -= answer[i];
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
