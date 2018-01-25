package on2016_02.on2016_02_07_Grand_Prix_of_Saratov.J___Sockets;



import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.*;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
        sort(a, IntComparator.REVERSE);
        sort(b, IntComparator.REVERSE);
        int left = 0;
        int right = m;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            int socket = middle - 1;
            int plug = 0;
            int level = 0;
            int curLevel = 1;
            while (socket >= 0) {
                while (socket >= 0 && b[socket] == level) {
                    curLevel--;
                    if (curLevel < 0) {
                        break;
                    }
                    socket--;
                }
                if (socket < 0 || curLevel < 0) {
                    break;
                }
                int nextLevel = 0;
                while (plug < n && curLevel > 0) {
                    nextLevel += a[plug];
                    plug++;
                    curLevel--;
                    nextLevel = Math.min(nextLevel, m);
                }
                if (curLevel >= 0) {
                    nextLevel += curLevel;
                }
                curLevel = nextLevel;
                if (curLevel > socket) {
                    socket = -1;
                    break;
                }
                level++;
            }
            if (socket < 0) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        out.printLine(left);
    }
}
