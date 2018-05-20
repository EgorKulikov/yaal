package on2018_05.on2018_05_May_Challenge_2018_Division_1.Fake_Binary_Search;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.util.Arrays.binarySearch;
import static net.egork.misc.ArrayUtils.sort;

public class FakeBinarySearch {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(n);
        IntHashMap index = new IntHashMap();
        for (int i = 0; i < n; i++) {
            index.put(a[i], i);
        }
        int[] sorted = a.clone();
        sort(sorted);
        for (int i = 0; i < q; i++) {
            int x = in.readInt();
            int at = index.get(x);
            int hasLess = binarySearch(sorted, x);
            int hasMore = n - hasLess - 1;
            int low = 0;
            int high = n - 1;
            int swapLess = 0;
            int swapMore = 0;
            int okLess = 0;
            int okMore = 0;
            int mid = 0;
            while (low <= high) {
                mid = (low + high) >> 1;
                if (mid == at) {
                    break;
                }
                if (mid > at) {
                    if (a[mid] > x) {
                        okMore++;
                    } else {
                        swapMore++;
                    }
                    high = mid - 1;
                } else {
                    if (a[mid] < x) {
                        okLess++;
                    } else {
                        swapLess++;
                    }
                    low = mid + 1;
                }
            }
            if (mid != at || okLess + swapLess > hasLess || okMore + swapMore > hasMore) {
                out.printLine(-1);
                continue;
            }
            out.printLine(max(swapLess, swapMore));
        }
    }
}
