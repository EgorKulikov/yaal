package on2016_04.on2016_04_22_Vekua_Cup_2016_Personal_Pre_Run.C___Chemistry;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.*;
import static java.util.Arrays.copyOf;
import static net.egork.misc.ArrayUtils.maxPosition;
import static net.egork.misc.ArrayUtils.minPosition;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] w = new int[5];
        double[] x = new double[5];
        double[] y = new double[5];
        double[] z = new double[5];
        for (int i = 0; i < 5; i++) {
            w[i] = in.readInt();
            if (w[i] == 0) {
                throw new UnknownError();
            }
            x[i] = in.readDouble();
            y[i] = in.readDouble();
            z[i] = in.readDouble();
        }
        for (int i = 0; i < 5; i++) {
            if (w[i] == 6) {
                for (int j = 0; j < 5; j++) {
                    if (i == j) {
                        continue;
                    }
                    x[j] -= x[i];
                    y[j] -= y[i];
                    z[j] -= z[i];
                }
                System.arraycopy(w, i + 1, w, i, 4 - i);
                System.arraycopy(x, i + 1, x, i, 4 - i);
                System.arraycopy(y, i + 1, y, i, 4 - i);
                System.arraycopy(z, i + 1, z, i, 4 - i);
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                if (w[i] == w[j]) {
                    out.printLine(0);
                    return;
                }
            }
        }
        w = copyOf(w, 4);
        int at = minPosition(w);
        double angle = atan2(x[at], z[at]);
        double cos = cos(angle);
        double sin = sin(angle);
        for (int i = 0; i < 4; i++) {
            double nz = z[i] * cos + x[i] * sin;
            double nx = -z[i] * sin + x[i] * cos;
            z[i] = nz;
            x[i] = nx;
        }
        angle = atan2(y[at], z[at]);
        cos = cos(angle);
        sin = sin(angle);
        for (int i = 0; i < 4; i++) {
            double nz = z[i] * cos + y[i] * sin;
            double ny = -z[i] * sin + y[i] * cos;
            z[i] = nz;
            y[i] = ny;
        }
        System.arraycopy(w, at + 1, w, at, 3 - at);
        System.arraycopy(x, at + 1, x, at, 3 - at);
        System.arraycopy(y, at + 1, y, at, 3 - at);
        w = copyOf(w, 3);
        at = maxPosition(w);
        double[] ang = new double[3];
        for (int i = 0; i < 3; i++) {
            ang[i] = atan2(y[i], x[i]);
        }
        for (int i = 0; i < 3; i++) {
            if (i != at) {
                while (ang[i] < ang[at]) {
                    ang[i] += Math.PI * 2;
                }
            }
        }
        int at2 = maxPosition(ang);
        if (w[at2] > w[3 - at2 - at]) {
            out.printLine(1);
        } else {
            out.printLine(2);
        }
    }
}
