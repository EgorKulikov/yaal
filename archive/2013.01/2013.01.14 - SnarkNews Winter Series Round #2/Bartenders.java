package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Bartenders {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int required = in.readInt();
        int count = in.readInt();
        int[] qty = new int[count];
        final int[] price = new int[count];
        int[] fee = new int[count];
        IOUtils.readIntArrays(in, qty, price, fee);
        int[] answer = new int[required + 1];
        Arrays.fill(answer, Integer.MAX_VALUE / 2);
        answer[0] = 0;
        int[] next = new int[required + 1];
        int[] mins = new int[required + 1];
        for (int i = 0; i < count; i++) {
            int start = 0;
            int end = 0;
            Arrays.fill(next, Integer.MAX_VALUE / 2);
            for (int j = 0; j <= required; j++) {
                while (start != end && j - mins[start] > qty[i])
                    start++;
                next[j] = answer[j];
                if (start != end)
                    next[j] = Math.min(next[j], answer[mins[start]] + fee[i] + (j - mins[start]) * price[i]);
                while (start != end) {
                    int tail = mins[end - 1];
                    if (answer[tail] - tail * price[i] > answer[j] - j * price[i])
                        end--;
                    else
                        break;
                }
                mins[end++] = j;
            }
            int[] temp = answer;
            answer = next;
            next = temp;
        }
        out.printLine(answer[required]);
    }
}
