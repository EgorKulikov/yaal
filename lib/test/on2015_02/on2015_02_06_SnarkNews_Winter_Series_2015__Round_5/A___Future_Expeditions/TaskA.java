package on2015_02.on2015_02_06_SnarkNews_Winter_Series_2015__Round_5.A___Future_Expeditions;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int cutoff = in.readInt();
		int good = in.readInt();
		int[] rating = IOUtils.readIntArray(in, count);
		int voteCount = in.readInt();
		int[] votes = IOUtils.readIntArray(in, voteCount);
		ArrayUtils.sort(votes);
		int[] order = ArrayUtils.order(rating);
		int left = 0;
		int right = good;
		while (left < right) {
			int middle = (left + right + 1) >> 1;
			int skip = good - middle;
			int at = voteCount - 1;
			int need = Integer.MAX_VALUE;
			for (int i : order) {
				if (i < good) {
					if (skip > 0) {
						skip--;
					} else if (at >= 0) {
						need = Math.min(need, rating[i] + votes[at--]);
					} else {
						need = Math.min(need, rating[i]);
					}
				}
			}
			at -= good - middle;
			int more = 0;
			for (int i = good; i < count; i++) {
				if (rating[i] > need) {
					more++;
					at--;
				}
			}
			if (at >= 0) {
				for (int i : order) {
					if (at >= 0 && i >= good && rating[i] <= need) {
						while (at >= 0 && rating[i] + votes[at--] > need) {
							more++;
						}
					}
				}
			}
			if (more + middle <= cutoff) {
				left = middle;
			} else {
				right = middle - 1;
			}
		}
		out.printLine(left);
    }

	private void sort(int[] currentOrder, int from, int to, int[] currentRating, int cutoff) {
		if (from >= cutoff || to < cutoff) {
			return;
		}
		if (from >= to) {
			return;
		}
		int at = (from + to) >> 1;
		int temp = currentOrder[at];
		currentOrder[at] = currentOrder[from];
		currentOrder[from] = temp;
		int next = from + 1;
		for (int i = from + 1; i <= to; i++) {
			if (currentRating[currentOrder[i]] > currentRating[currentOrder[from]] ||
				currentRating[currentOrder[i]] == currentRating[currentOrder[from]] && currentOrder[i] > currentOrder[from])
			{
				temp = currentOrder[next];
				currentOrder[next++] = currentOrder[i];
				currentOrder[i] = temp;
			}
		}
		temp = currentOrder[next - 1];
		currentOrder[next - 1] = currentOrder[from];
		currentOrder[from] = temp;
		if (next >= cutoff) {
			sort(currentOrder, from, next - 2, currentRating, cutoff);
		} else {
			sort(currentOrder, next, to, currentRating, cutoff);
		}
	}
}
