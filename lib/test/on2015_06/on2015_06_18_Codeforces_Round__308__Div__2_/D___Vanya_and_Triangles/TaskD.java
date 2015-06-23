package on2015_06.on2015_06_18_Codeforces_Round__308__Div__2_.D___Vanya_and_Triangles;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] x = new int[count];
        int[] y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        int[] a = new int[count * (count - 1) / 2];
        int[] b = new int[a.length];
        int[] c = new int[a.length];
        int at = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                a[at] = y[i] - y[j];
                b[at] = x[j] - x[i];
                c[at] = -a[at] * x[i] - b[at] * y[i];
                int g = IntegerUtils.gcd(IntegerUtils.gcd(a[at], b[at]), c[at]);
                a[at] /= g;
                b[at] /= g;
                c[at] /= g;
                if (a[at] < 0) {
                    a[at] *= -1;
                    b[at] *= -1;
                    c[at] *= -1;
                } else if (a[at] == 0 && b[at] < 0) {
                    b[at] *= -1;
                    c[at] *= -1;
                }
                at++;
            }
        }
        int[] order = ArrayUtils.createOrder(a.length);
        ArrayUtils.sort(order, (f, s) -> a[f] == a[s] ? (b[f] == b[s] ? c[f] - c[s] : b[f] - b[s]) : a[f] - a[s]);
        long answer = (long) count * (count - 1) * (count - 2) / 6;
        int start = 0;
        long qty = 0;
        for (int i : order) {
            if (a[i] != a[start] || b[i] != b[start] || c[i] != c[start]) {
                qty *= 2;
                long onLine = Math.round(Math.ceil(Math.sqrt(qty)));
                answer -= onLine * (onLine - 1) * (onLine - 2) / 6;
                qty = 0;
                start = i;
            }
            qty++;
        }
        qty *= 2;
        long onLine = Math.round(Math.ceil(Math.sqrt(qty)));
        answer -= onLine * (onLine - 1) * (onLine - 2) / 6;
        out.printLine(answer);
    }
}
