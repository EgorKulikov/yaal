package on2013_02.on2013_02_28_Codeforces_Round__170.D___Google_Code_Jam;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int time = in.readInt();
		int[] scoreSmall = new int[count];
		int[] scoreLarge = new int[count];
		int[] timeSmall = new int[count];
		final int[] timeLarge = new int[count];
		final double[] fail = new double[count];
		for (int i = 0; i < count; i++) {
			scoreSmall[i] = in.readInt();
			scoreLarge[i] = in.readInt();
			timeSmall[i] = in.readInt();
			timeLarge[i] = in.readInt();
			fail[i] = in.readDouble();
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return Double.compare(fail[first] * timeLarge[first] + fail[first] * fail[second] * timeLarge[second], fail[second] * timeLarge[second] + fail[first] * fail[second] * timeLarge[first]);
			}
		});
		long[] bestScore = new long[time + 1];
		double[] bestTime = new double[time + 1];
		for (int i : order) {
			long realScoreSmall = scoreSmall[i] * 1000000L;
			long realScoreLarge = Math.round(scoreLarge[i] * 1000000L * (1 - fail[i]));
			for (int j = time; j >= 0; j--) {
				if (j + timeSmall[i] <= time) {
					int next = j + timeSmall[i];
					long score = bestScore[j] + realScoreSmall;
					if (score > bestScore[next]) {
						bestScore[next] = score;
						bestTime[next] = bestTime[j];
					} else if (score == bestScore[next])
						bestTime[next] = Math.max(bestTime[next], bestTime[j]);
				}
				if (j + timeSmall[i] + timeLarge[i] <= time) {
					int next = j + timeSmall[i] + timeLarge[i];
					long score = bestScore[j] + realScoreSmall + realScoreLarge;
					if (score > bestScore[next]) {
						bestScore[next] = score;
						bestTime[next] = fail[i] * (timeLarge[i] + bestTime[j]);
					} else if (score == bestScore[next])
						bestTime[next] = Math.max(bestTime[next], fail[i] * (timeLarge[i] + bestTime[j]));
				}
			}
		}
		long answerScore = -1;
		double answerTime = 0;
		for (int i = 0; i <= time; i++) {
			if (bestScore[i] > answerScore) {
				answerScore = bestScore[i];
				answerTime = i - bestTime[i];
			} else if (bestScore[i] == answerScore)
				answerTime = Math.min(answerTime, i - bestTime[i]);
		}
		out.printFormat("%.15f %.15f\n", answerScore / 1e6, answerTime);
    }
}
