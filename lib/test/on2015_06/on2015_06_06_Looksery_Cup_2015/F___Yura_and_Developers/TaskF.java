package on2015_06.on2015_06_06_Looksery_Cup_2015.F___Yura_and_Developers;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
//        int count = 300000;
        int mod = in.readInt();
//        int mod = 1000000;
        int[] work = IOUtils.readIntArray(in, count);
//        Random random = new Random(239);
//        int[] work = new int[count];
//        for (int i = 0; i < count; i++) {
//            work[i] = random.nextInt(1000000000) + 1;
//        }
        int[] order = ArrayUtils.order(work);
        for (int i = 0; i < count; i++) {
            work[i] %= mod;
        }
        int[] id = new int[count];
        Arrays.fill(id, -1);
        int[] start = new int[count];
        int[] end = new int[count];
        int[][] positions = new int[mod][];
        int[] size = new int[mod];
        long[] sums = ArrayUtils.partialSums(work);
        for (int i = 0; i <= count; i++) {
            sums[i] %= mod;
            size[((int) sums[i])]++;
        }
        for (int i = 0; i < mod; i++) {
            positions[i] = new int[size[i]];
        }
        for (int i = count; i >= 0; i--) {
            positions[((int) sums[i])][--size[((int) sums[i])]] = i;
        }
        long answer = 0;
        for (int i : order) {
            id[i] = i;
            start[i] = i;
            end[i] = i + 1;
            int leftStart = i;
            if (i > 0 && id[i - 1] != -1) {
                leftStart = start[id[i - 1]];
            }
            int rightEnd = i + 1;
            if (i < count - 1 && id[i + 1] != -1) {
                rightEnd = end[id[i + 1]];
            }
            if (leftStart == i && rightEnd == i + 1) {
                continue;
            }
            answer--;
            if (i - leftStart < rightEnd - (i + 1)) {
                for (int j = leftStart; j <= i; j++) {
                    int target = (int) (sums[j] + work[i]);
                    if (target >= mod) {
                        target -= mod;
                    }
                    int left = Arrays.binarySearch(positions[target], i + 1);
                    if (left < 0) {
                        left = -left - 1;
                    }
                    int right = Arrays.binarySearch(positions[target], rightEnd);
                    if (right < 0) {
                        right = -right - 2;
                    }
                    answer += right - left + 1;
                    id[j] = id[i + 1];
                }
            } else {
                for (int j = i; j < rightEnd; j++) {
                    int target = (int) (sums[j + 1] - work[i]);
                    if (target < 0) {
                        target += mod;
                    }
                    int left = Arrays.binarySearch(positions[target], leftStart);
                    if (left < 0) {
                        left = -left - 1;
                    }
                    int right = Arrays.binarySearch(positions[target], i);
                    if (right < 0) {
                        right = -right - 2;
                    }
                    answer += right - left + 1;
                    id[j] = id[i - 1];
                }
            }
            start[id[i]] = leftStart;
            end[id[i]] = rightEnd;
        }
        out.printLine(answer);
    }
}
