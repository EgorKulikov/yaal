import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int dayCount = in.readInt();
		int studyCount = in.readInt();
		int delta = in.readInt();
		long[] min = new long[studyCount];
		long[] max = new long[studyCount];
		long[] hardness = new long[studyCount];
		IOUtils.readLongArrays(in, min, max, hardness);
		long[][][] total = new long[dayCount][studyCount][101];
		long[][][] last = new long[dayCount][studyCount][101];
		ArrayUtils.fill(total, Long.MIN_VALUE / 2);
		for (int i = 0; i < studyCount; i++) {
			for (int j = 0; j <= max[i] - min[i]; j++)
				total[0][i][j] = min[i] + j;
		}
		for (int i = 1; i < dayCount; i++) {
			for (int j = 0; j < studyCount; j++) {
				for (int k = 0; k <= max[j] - min[j]; k++) {
					for (int l = 0; l < studyCount; l++) {
						if (hardness[l] >= hardness[j])
							continue;
						long index = min[j] + k - delta - min[l];
						if (index >= 0 && index <= max[l] - min[l]) {
							long value = total[i - 1][l][((int) index)] + k + min[j];
							if (value > total[i][j][k]) {
								total[i][j][k] = value;
								last[i][j][k] = l + (index << 7);
							}
						}
						index = min[j] + k;
						if (index % delta != 0)
							continue;
						index = index / delta - min[l];
						if (index >= 0 && index <= max[l] - min[l]) {
							long value = total[i - 1][l][((int) index)] + k + min[j];
							if (value > total[i][j][k]) {
								total[i][j][k] = value;
								last[i][j][k] = l + (index << 7);
							}
						}
					}
				}
			}
		}
		long result = -1;
		long[][] longs = total[dayCount - 1];
		int lastStudy = -1;
		int count = -1;
		for (int i = 0, longsLength = longs.length; i < longsLength; i++) {
			long[] row = longs[i];
			int index = SequenceUtils.maxIndex(Array.wrap(row));
			if (row[index] > result) {
				result = row[index];
				lastStudy = i;
				count = index;
			}
		}
		if (result < 0) {
			out.println("NO");
			return;
		}
		int[] index = new int[dayCount];
		long[] homework = new long[dayCount];
		for (int i = dayCount - 1; i >= 0; i--) {
			index[i] = lastStudy + 1;
			homework[i] = min[lastStudy] + count;
			int nextStudy = (int) (last[i][lastStudy][count] & 127);
			int nextCount = (int) (last[i][lastStudy][count] >> 7);
			lastStudy = nextStudy;
			count = nextCount;
		}
		out.println("YES");
		for (int i = 0; i < dayCount; i++)
			out.println(index[i] + " " + homework[i]);
	}
}

