package on2015_08.on2015_08_18_SNSS_2015_Round_3.A_______________;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    int n;
    int[] d;
    int[] u;
    int[][] children;
    int[] taken;
    int[] available;
    boolean[] inAnswer;
    int[] queue;
    int size;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        d = new int[n];
        u = new int[n];
        children = new int[n][];
        for (int i = 0; i < n; i++) {
            d[i] = in.readInt();
            u[i] = in.readInt();
            children[i] = IOUtils.readIntArray(in, in.readInt());
        }
        MiscUtils.decreaseByOne(children);
        taken = new int[n];
        available = new int[n];
        inAnswer = new boolean[n];
        queue = new int[n];
        process(0);
        out.printLine(taken[0]);
        for (int i = 0; i < n; i++) {
            if (inAnswer[i]) {
                out.print((i + 1) + " ");
            }
        }
        out.printLine();
    }

    private void process(int current) {
        queue[size++] = current;
        int totalAvailable = 1;
        int totalTaken = 0;
        for (int i : children[current]) {
            process(i);
            totalTaken += taken[i];
            totalAvailable += available[i];
        }
        while (totalTaken < d[current]) {
            inAnswer[queue[--size]] = true;
            totalTaken++;
            totalAvailable--;
        }
        while (totalTaken + totalAvailable > u[current]) {
            size--;
            totalAvailable--;
        }
        taken[current] = totalTaken;
        available[current] = totalAvailable;
    }
}
