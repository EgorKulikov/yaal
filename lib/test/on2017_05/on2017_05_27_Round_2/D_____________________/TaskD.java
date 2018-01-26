package on2017_05.on2017_05_27_Round_2.D_____________________;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static net.egork.misc.MiscUtils.DX4;
import static net.egork.misc.MiscUtils.DY4;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] score = new int[1000][1000];
        Set<IntIntPair> present = new HashSet<>();
        Queue<IntIntPair> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = in.readInt() + 500;
            int y = in.readInt() + 500;
            score[x][y]++;
            if (score[x][y] >= 5) {
                queue.add(new IntIntPair(x, y));
            } else {
                present.add(new IntIntPair(x, y));
            }
            while (!queue.isEmpty()) {
                IntIntPair key = queue.poll();
                x = key.first;
                y = key.second;
                int add = score[x][y] / 5;
                score[x][y] %= 5;
                if (score[x][y] > 0) {
                    present.add(key);
                } else {
                    present.remove(key);
                }
                for (int j = 0; j < 4; j++) {
                    int nx = x + DX4[j];
                    int ny = y + DY4[j];
                    score[nx][ny] += add;
                    if (score[nx][ny] >= 5) {
                        queue.add(new IntIntPair(nx, ny));
                    } else {
                        present.add(new IntIntPair(nx, ny));
                    }
                }
            }
            out.printLine(present.size());
        }
    }
}
