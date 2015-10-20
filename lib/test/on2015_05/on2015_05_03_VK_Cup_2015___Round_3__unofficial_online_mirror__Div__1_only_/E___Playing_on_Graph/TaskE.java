package on2015_05.on2015_05_03_VK_Cup_2015___Round_3__unofficial_online_mirror__Div__1_only_.E___Playing_on_Graph;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        int count = 1000;
//        int edgeCount = 100000;
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[][] distance = new int[count][count];
        ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
        for (int i = 0; i < count; i++) {
            distance[i][i] = 0;
        }
//        Random random = new Random(239);
        for (int i = 0; i < edgeCount; i++) {
//            int from = 2 * random.nextInt(500);
//            int to = 2 * random.nextInt(500) + 1;
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            distance[from][to] = distance[to][from] = 1;
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = j + 1; k < i; k++) {
                    distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[k][i]);
                }
            }
            for (int j = i + 1; j < count; j++) {
                for (int k = j + 1; k < count; k++) {
                    distance[j][k] = Math.min(distance[j][k], distance[i][j] + distance[i][k]);
                }
            }
            for (int j = 0; j < i; j++) {
                for (int k = i + 1; k < count; k++) {
                    distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
                }
            }
//            for (int j = 0; j < count; j++) {
//                for (int k = 0; k < j; k++) {
//                    distance[k][j] = distance[j][k] = Math.min(distance[j][k], distance[i][j] + distance[i][k]);
//                }
//            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                distance[i][j] = distance[j][i];
            }
        }
        int answer = 0;
        boolean[] processed = new boolean[count];
        for (int i = 0; i < count; i++) {
            if (processed[i]) {
                continue;
            }
            IntList curList = new IntArrayList();
            for (int j = 0; j < count; j++) {
                if (distance[i][j] != Integer.MAX_VALUE / 2) {
                    curList.add(j);
                    processed[j] = true;
                }
            }
            int[] current = curList.toArray();
            int max = 0;
            for (int j : current) {
                for (int k : current) {
                    if ((distance[i][j] + distance[i][k] + distance[j][k]) % 2 == 1) {
                        out.printLine(-1);
                        return;
                    }
                    max = Math.max(max, distance[j][k]);
                }
            }
            answer += max;
        }
        out.printLine(answer);
    }
}
