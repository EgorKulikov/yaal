package on2015_06.on2015_06_14_RCC_2015_________________.B_______________________;


import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] team = IOUtils.readIntArray(in, count);
        int[][] relationship = new int[count][count];
        for (int i = 0; i < edgeCount; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            int type = 1 - 2 * in.readInt();
            relationship[from][to] = relationship[to][from] = type;
        }
        if (count % 2 == 1) {
            out.printLine("NO");
            return;
        }
        int sureFirst = 0;
        int sureSecond = 0;
        boolean[] processed = new boolean[count];
        List<IntIntPair> notSure = new ArrayList<>();
        List<int[]> indices = new ArrayList<>();
        int[] queue = new int[count];
        for (int i = 0; i < 2 * count; i++) {
            int current = i >= count ? i - count : i;
            if (i < count && team[i] == 0 || processed[current]) {
                continue;
            }
            queue[0] = current;
            processed[current] = true;
            int size = 1;
            if (team[current] == 0) {
                team[current] = 1;
            }
            for (int j = 0; j < size; j++) {
                current = queue[j];
                for (int k = 0; k < count; k++) {
                    if (relationship[current][k] == 1) {
                        if (team[k] == 3 - team[current]) {
                            out.printLine("NO");
                            return;
                        }
                        if (team[k] == 0) {
                            team[k] = team[current];
                            processed[k] = true;
                            queue[size++] = k;
                        }
                    } else if (relationship[current][k] == -1) {
                        if (team[k] == team[current]) {
                            out.printLine("NO");
                            return;
                        }
                        if (team[k] == 0) {
                            team[k] = 3 - team[current];
                            processed[k] = true;
                            queue[size++] = k;
                        }
                    }
                }
            }
            int first = 0;
            int second = 0;
            for (int j = 0; j < size; j++) {
                if (team[queue[j]] == 1) {
                    first++;
                } else {
                    second++;
                }
            }
            if (i < count) {
                sureFirst += first;
                sureSecond += second;
            } else {
                notSure.add(new IntIntPair(first, second));
                indices.add(Arrays.copyOf(queue, size));
            }
        }
        int teamSize = count / 2;
        if (sureFirst > teamSize || sureSecond > teamSize) {
            out.printLine("NO");
            return;
        }
        int total = sureFirst + sureSecond;
        int[][] reachable = new int[teamSize + 1][teamSize + 1];
        reachable[sureFirst][sureSecond] = 1;
        for (IntIntPair pair : notSure) {
            for (int i = 0; i <= total; i++) {
                int j = total - i;
                if (i > teamSize || j > teamSize || reachable[i][j] == 0) {
                    continue;
                }
                if (i + pair.first <= teamSize && j + pair.second <= teamSize) {
                    reachable[i + pair.first][j + pair.second] = 1;
                }
                if (i + pair.second <= teamSize && j + pair.first <= teamSize) {
                    reachable[i + pair.second][j + pair.first] = -1;
                }
            }
            total += pair.first + pair.second;
        }
        if (reachable[teamSize][teamSize] == 0) {
            out.printLine("NO");
            return;
        }
        Collections.reverse(notSure);
        Collections.reverse(indices);
        int curFirst = teamSize;
        int curSecond = teamSize;
        int at = 0;
        for (IntIntPair pair : notSure) {
            if (reachable[curFirst][curSecond] == -1) {
                for (int i : indices.get(at)) {
                    team[i] = 3 - team[i];
                }
                curFirst -= pair.second;
                curSecond -= pair.first;
            } else {
                curFirst -= pair.first;
                curSecond -= pair.second;
            }
            at++;
        }
        out.printLine("YES");
        out.printLine(team);
    }
}
