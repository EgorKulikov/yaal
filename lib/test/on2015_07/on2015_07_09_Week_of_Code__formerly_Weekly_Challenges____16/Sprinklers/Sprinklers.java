package on2015_07.on2015_07_09_Week_of_Code__formerly_Weekly_Challenges____16.Sprinklers;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class Sprinklers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int s = in.readInt();
        int q = in.readInt();
        int[] p = IOUtils.readIntArray(in, m);
        MiscUtils.decreaseByOne(p);
        int answer = Integer.MAX_VALUE;
        IntList positions = null;
        int b = -1;
        int e = -1;
        for (int i = 0; i < n && i * q + s < answer; i++) {
            if (p[0] > i) {
                continue;
            }
            int until = i;
            int last = -1;
            int current = i * q;
            boolean good = true;
            int distance = 2 * i + 1;
            IntList cPos = new IntArrayList();
            for (int j : p) {
                if (j > until) {
                    until = last + distance;
                    if (j > until) {
                        good = false;
                        break;
                    }
                    cPos.add(last + 1);
                    current += s;
                }
                last = j;
            }
            if (until - distance + i < n - 1) {
                until = last + i;
                if (until < n - 1) {
                    good = false;
                }
                current += s;
                cPos.add(last + 1);
            }
            if (good && answer > current) {
                answer = current;
                positions = cPos;
                b = positions.size();
                e = i;
            }
        }
        out.printLine(b, e);
        out.printLine(positions);
        GeometryUtils.Inner inner = new GeometryUtils.Inner();
        net.egork.geometry.GeometryUtils.canonicalAngle(0);
        List<Integer> list = null;
    }
}

class GeometryUtils {
    static class Inner {
        public Inner() {
        }
    }

    public GeometryUtils() {
    }
}