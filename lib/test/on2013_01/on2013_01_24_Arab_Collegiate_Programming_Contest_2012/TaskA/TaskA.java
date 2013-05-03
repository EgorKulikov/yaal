package on2013_01.on2013_01_24_Arab_Collegiate_Programming_Contest_2012.TaskA;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int candidateCount = in.readInt();
		int voterCount = in.readInt();
		int[][] votes = IOUtils.readIntTable(in, voterCount, candidateCount);
		MiscUtils.decreaseByOne(votes);
		int[] qty = new int[candidateCount];
		for (int i = 0; i < voterCount; i++)
			qty[votes[i][0]]++;
		int maxIndex = ArrayUtils.maxPosition(qty);
		if (qty[maxIndex] * 2 > voterCount) {
			out.printLine(maxIndex + 1, 1);
			return;
		}
		qty[maxIndex] = 0;
		int secondMax = ArrayUtils.maxPosition(qty);
		int votesFirst = 0;
		int votesSecond = 0;
		for (int i = 0; i < voterCount; i++) {
			for (int j : votes[i]) {
				if (j == maxIndex) {
					votesFirst++;
					break;
				} else if (j == secondMax) {
					votesSecond++;
					break;
				}
			}
		}
		if (votesFirst > votesSecond)
			out.printLine(maxIndex + 1, 2);
		else
			out.printLine(secondMax + 1, 2);
	}
}
