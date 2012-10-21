package on2012_08.on2012_7_7.coingame;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CoinGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int startState = 0;
        for (int i = 0; i < count; i++)
            startState += i << (i * 3);
        int[] result = new int[1 << (count * 3)];
        Arrays.fill(result, -1);
        int[] queue = new int[1 << (count * 3)];
        queue[0] = startState;
        result[startState] = 0;
        int size = 1;
        int maskLength = 3 * count;
        for (int i = 0; i < size; i++) {
            int state = queue[i];
            int used = 0;
            for (int j = 0; j < maskLength; j += 3) {
                int current = state >> j & 7;
                if ((used >> current & 1) != 0)
                    continue;
                used += 1 << current;
                if (current != 0 && (used >> (current - 1) & 1) == 0) {
                    int nextState = state - (1 << j);
                    if (result[nextState] == -1) {
                        result[nextState] = result[state] + 1;
                        queue[size++] = nextState;
                    }
                }
                if (current != count - 1 && (used >> (current + 1) & 1) == 0) {
                    int nextState = state + (1 << j);
                    if (result[nextState] == -1) {
                        result[nextState] = result[state] + 1;
                        queue[size++] = nextState;
                    }
                }
            }
        }
        int[] order = IOUtils.readIntArray(in, count);
        MiscUtils.decreaseByOne(order);
        int state = 0;
        for (int i = 0; i < count; i++)
            state += i << (order[i] * 3);
        out.printLine(result[state]);
	}
}
