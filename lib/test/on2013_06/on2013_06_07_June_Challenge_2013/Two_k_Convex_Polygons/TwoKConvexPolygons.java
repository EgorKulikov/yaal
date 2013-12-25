package on2013_06.on2013_06_07_June_Challenge_2013.Two_k_Convex_Polygons;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TwoKConvexPolygons {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.order(lengths);
		Arrays.sort(lengths);
		long[] sum = ArrayUtils.partialSums(lengths);
		for (int i = size - 1; i < count; i++) {
			if (sum[i] - sum[i - size + 1] <= lengths[i])
				continue;
			for (int j = i + size; j < count; j++) {
				if (sum[j] - sum[j - size + 1] > lengths[j]) {
					out.printLine("Yes");
					int[] answer = new int[2 * size];
					for (int k = 0; k < size; k++) {
						answer[k] = order[i - size + 1 + k] + 1;
						answer[k + size] = order[j - size + 1 + k] + 1;
					}
					out.printLine(answer);
					return;
				}
			}
			for (int j = Math.max(i + 1, 2 * size - 1); j < i + size && j < count; j++) {
				long additional = sum[j] - sum[i + 1];
				int length = 2 * size - 2 - (j - i - 1);
				if (additional + sum[i] - sum[i - length] <= lengths[i] + lengths[j] + 1)
					continue;
				for (int k = 0; k < (1 << length); k++) {
					if (Integer.bitCount(k) == size - 1) {
						long left = 0;
						long right = additional;
						for (int l = 0; l < length; l++) {
							if ((k >> l & 1) == 1)
								left += lengths[i - 1 - l];
							else
								right += lengths[i - l - 1];
						}
						if (left > lengths[i] && right > lengths[j]) {
							out.printLine("Yes");
							int[] answer = new int[2 * size];
							int i1 = 0;
							int i2 = size;
							for (int l = length - 1; l >= 0; l--) {
								if ((k >> l & 1) == 1)
									answer[i1++] = order[i - l - 1] + 1;
								else
									answer[i2++] = order[i - l - 1] + 1;
							}
							answer[size - 1] = order[i] + 1;
							for (int l = i + 1; l <= j; l++)
								answer[i2++] = order[l] + 1;
							out.printLine(answer);
							return;
						}
					}
				}
			}
		}
		out.printLine("No");
    }
}
