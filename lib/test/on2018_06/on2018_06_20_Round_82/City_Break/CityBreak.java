package on2018_06.on2018_06_20_Round_82.City_Break;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.misc.ArrayUtils.partialSums;

public class CityBreak {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt() - 1;
        int[] distance = in.readIntArray(n);
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
        long answer = 0;
        long[] sums = partialSums(distance);
        while (set.size() > 1) {
            set.remove(s);
            Integer left = set.lower(s);
            long leftDistance;
            if (left == null) {
                left = set.last();
                leftDistance = sums[s] - sums[left] + sums[n];
            } else {
                leftDistance = sums[s] - sums[left];
            }
            Integer right = set.higher(s);
            long rightDistance;
            if (right == null) {
                right = set.first();
                rightDistance = sums[right] - sums[s] + sums[n];
            } else {
                rightDistance = sums[right] - sums[s];
            }
            if (leftDistance < rightDistance || leftDistance == rightDistance && left < right) {
                answer += leftDistance;
                s = left;
            } else {
                answer += rightDistance;
                s = right;
            }
        }
        out.printLine(answer);
    }
}
