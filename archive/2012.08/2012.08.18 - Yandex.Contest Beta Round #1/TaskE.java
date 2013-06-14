package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    private static final int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int money = in.readInt();
        int[] cost = IOUtils.readIntArray(in, count);
        int sumCost = (int) ArrayUtils.sumArray(cost);
        if (sumCost < money) {
            out.printLine(0);
            return;
        }
        int[] answer = new int[sumCost + 1];
        answer[0] = 1;
        int curSum = 0;
        for (int i : cost) {
            for (int j = curSum; j >= 0; j--) {
                if (answer[j] != 0) {
                    answer[i + j] += answer[j];
                    if (answer[i + j] >= MOD)
                        answer[i + j] -= MOD;
                }
            }
            curSum += i;
        }
        long result = 0;
        for (int i = money; i <= sumCost; i++)
            result += answer[i];
        result %= MOD;
        out.printLine(result);
	}
}
