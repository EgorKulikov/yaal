package on2015_06.on2015_06_June_Challenge_2015.Chef_and_Polygons;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndPolygons {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] x = new int[count];
        for (int i = 0; i < count; i++) {
            x[i] = Integer.MAX_VALUE;
            int size = in.readInt();
            for (int j = 0; j < size; j++) {
                x[i] = Math.min(x[i], in.readInt());
                in.readInt();
            }
        }
        int[] order = ArrayUtils.order(x);
        int[] answer = ArrayUtils.reversePermutation(order);
        for (int i = 0; i < count; i++) {
            answer[i] = count - 1 - answer[i];
        }
        out.printLine(answer);
    }
}
