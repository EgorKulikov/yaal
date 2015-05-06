package on2015_05.on2015_05_03_VK_Cup_2015___Round_3__unofficial_online_mirror__Div__1_only_.A___Place_Your_Ad_Here;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int clipCount = in.readInt();
        int tvCount = in.readInt();
        int[] left = new int[clipCount];
        int[] right = new int[clipCount];
        IOUtils.readIntArrays(in, left, right);
        int[] from = new int[tvCount];
        int[] to = new int[tvCount];
        int[] qty = new int[tvCount];
        IOUtils.readIntArrays(in, from, to, qty);
        long answer = 0;
        int bestClip = -1;
        int bestTv = -1;
        int[] clipId = ArrayUtils.createOrder(clipCount);
        int[] tvId = ArrayUtils.createOrder(tvCount);
        ArrayUtils.orderBy(left, right, clipId);
        ArrayUtils.orderBy(from, to, qty, tvId);
        int at = 0;
        int rightMost = -1;
        for (int i = 0; i < tvCount; i++) {
            while (at < clipCount && left[at] <= from[i]) {
                if (rightMost == -1 || right[at] > right[rightMost]) {
                    rightMost = at;
                }
                at++;
            }
            int length = rightMost == -1 ? 0 : Math.max(Math.min(right[rightMost], to[i]) - from[i], 0);
            long value = (long)length * qty[i];
            if (value > answer) {
                answer = value;
                bestClip = clipId[rightMost] + 1;
                bestTv = tvId[i] + 1;
            }
        }
        ArrayUtils.orderBy(right, left, clipId);
        ArrayUtils.orderBy(to, from, qty, tvId);
        at = clipCount - 1;
        int leftMost = -1;
        for (int i = tvCount - 1; i >= 0; i--) {
            while (at >= 0 && right[at] >= to[i]) {
                if (leftMost == -1 || left[at] < left[leftMost]) {
                    leftMost = at;
                }
                at--;
            }
            int length = leftMost == -1 ? 0 : Math.max(to[i] - Math.max(left[leftMost], from[i]), 0);
            long value = (long)length * qty[i];
            if (value > answer) {
                answer = value;
                bestClip = clipId[leftMost] + 1;
                bestTv = tvId[i] + 1;
            }
        }
        left = Arrays.copyOf(left, clipCount + 1);
        final int[] finalLeft = left;
        NavigableSet<Integer> set = new TreeSet<>((a, b) -> finalLeft[a] - finalLeft[b]);
        at = 0;
        for (int i = 0; i < tvCount; i++) {
            while (at < clipCount && right[at] <= to[i]) {
                Integer toRight = set.ceiling(at);
                if (toRight != null && right[toRight] - left[toRight] >= right[at] - left[at]) {
                    at++;
                    continue;
                }
                while (true) {
                    Integer toLeft = set.lower(at);
                    if (toLeft == null || right[toLeft] - left[toLeft] > right[at] - left[at]) {
                        break;
                    }
                    set.remove(toLeft);
                }
                set.add(at++);
            }
            left[clipCount] = from[i];
            Integer id = set.ceiling(clipCount);
            if (id != null) {
                long value = (long)(right[id] - left[id]) * qty[i];
                if (value > answer) {
                    answer = value;
                    bestClip = clipId[id] + 1;
                    bestTv = tvId[i] + 1;
                }
            }
        }
        out.printLine(answer);
        if (answer != 0) {
            out.printLine(bestClip, bestTv);
        }
    }
}
