package on2014_12.on2014_12_24_Codeforces_Round__284__Div__1_.A___Crazy_Town;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Point origin = Point.readPoint(in);
        Point destination = Point.readPoint(in);
        int count = in.readInt();
        int answer = 0;
        for (int i = 0; i < count; i++) {
            int a = in.readInt();
            int b = in.readInt();
            int c = in.readInt();
            Line line = new Line(a, b, c);
            if (line.value(origin) * line.value(destination) < 0) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
