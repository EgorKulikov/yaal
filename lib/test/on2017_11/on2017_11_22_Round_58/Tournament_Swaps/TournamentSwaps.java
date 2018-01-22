package on2017_11.on2017_11_22_Round_58.Tournament_Swaps;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TournamentSwaps {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] order = in.readIntArray(1 << n);
        decreaseByOne(order);
        int[][] winner = new int[n][];
        int[][] second = new int[n][];
        winner[0] = new int[1 << (n - 1)];
        second[0] = new int[1 << (n - 1)];
        for (int i = 0; i < (1 << (n - 1)); i++) {
            winner[0][i] = max(order[2 * i], order[2 * i + 1]);
            second[0][i] = min(order[2 * i], order[2 * i + 1]);
        }
        for (int i = 1; i < n; i++) {
            winner[i] = new int[winner[i - 1].length >> 1];
            second[i] = new int[winner[i].length];
            for (int j = 0; j < winner[i].length; j++) {
                if (winner[i - 1][2 * j] > winner[i - 1][2 * j + 1]) {
                    winner[i][j] = winner[i - 1][2 * j];
                    second[i][j] = max(winner[i - 1][2 * j + 1], second[i - 1][2 * j]);
                } else {
                    winner[i][j] = winner[i - 1][2 * j + 1];
                    second[i][j] = max(winner[i - 1][2 * j], second[i - 1][2 * j + 1]);
                }
            }
        }
        int[] best = new int[n];
        int[] secondBest = new int[n];
        int[] bestAt = new int[n];
        for (int i = 0; i < n; i++) {
            best[i] = second[i][0];
            bestAt[i] = 0;
            secondBest[i] = winner[i][0];
            for (int j = 1; j < winner[i].length; j++) {
                if (second[i][j] < best[i]) {
                    secondBest[i] = min(best[i], winner[i][j]);
                    bestAt[i] = j;
                    best[i] = second[i][j];
                } else {
                    secondBest[i] = Math.min(secondBest[i], second[i][j]);
                }
            }
        }
        int[] reverse = ArrayUtils.reversePermutation(order);
        int[] answer = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if (i < best[j] || (reverse[i] >> (j + 1)) == bestAt[j] && i < secondBest[j]) {
                    break;
                }
                answer[i]++;
            }
        }
        out.printLine(answer);
    }
}
