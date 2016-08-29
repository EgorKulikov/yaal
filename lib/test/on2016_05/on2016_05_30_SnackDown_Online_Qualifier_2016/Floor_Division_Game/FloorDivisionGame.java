package on2016_05.on2016_05_30_SnackDown_Online_Qualifier_2016.Floor_Division_Game;



import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class FloorDivisionGame {
    LongList limits;
    Map<Long, Integer> grundi = new HashMap<>();

    {
        limits = new LongArrayList();
        limits.add(0);
        limits.add(1);
        limits.add(2);
        limits.add(4);
        limits.add(6);
        long current = 2;
        long until = 6;
        int at = 0;
        while (until <= 1000000000000000000L) {
            if (at == 0) {
                current *= 3;
            } else if (at != 3) {
                current *= 2;
            }
            at++;
            if (at == 4) {
                at = 0;
            }
            until += current;
            limits.add(until);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        /*int[] result = new int[100000];
        for (int i = 0; i < 100000; i++) {
            result[i] = solve(i);
        }
        IntList same = new IntArrayList();
        int start = 0;
        int current = 0;
        for (int i = 0; i < 100000; i++) {
            if (result[i] != current) {
                same.add(i - start);
                start = i;
                current = result[i];
            }
        }
        out.printLine(same);*/
        int n = in.readInt();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            long current = in.readLong();
            int value = limits.binarySearch(x -> x > current) - 1;
            answer ^= value & 3;
        }
        out.printLine(answer != 0 ? "Henry" : "Derek");
    }

    private int solve(long number) {
        Integer result = grundi.get(number);
        if (result != null) {
            return result;
        }
        int mask = 0;
        for (int i = 2; i <= 6; i++) {
            mask |= 1 << solve(number / i);
        }
        for (int i = 0; ; i++) {
            if ((mask >> i & 1) == 0) {
                grundi.put(number, i);
                return i;
            }
        }
    }
}
