package on2017_01.on2017_01_15_8VC_Venture_Cup_2017___Elimination_Round.D___PolandBall_and_Polygon;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        k = min(k, n - k);
        IntervalTree tree = new SumIntervalTree(n);
        long answer = 1;
        int at = 0;
        for (int i = 0; i < n; i++) {
            int next = at + k;
            if (next < n) {
                answer += tree.query(at + 1, next - 1);
            } else {
                answer += tree.query(at + 1, n - 1);
                next -= n;
                answer += tree.query(0, next - 1);
            }
            answer++;
            tree.update(at, at, 1);
            tree.update(next, next, 1);
            at = next;
            out.printLine(answer);
        }
    }
}
