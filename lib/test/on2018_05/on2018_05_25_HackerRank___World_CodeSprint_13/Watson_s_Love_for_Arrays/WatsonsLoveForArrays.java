package on2018_05.on2018_05_25_HackerRank___World_CodeSprint_13.Watson_s_Love_for_Arrays;



import net.egork.collections.map.Counter;
import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class WatsonsLoveForArrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        long answer;
        if (k == 0) {
            answer = (long)n * (n + 1) / 2;
            int last = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] % m == 0) {
                    long length = i - last - 1;
                    answer -= length * (length + 1) / 2;
                    last = i;
                }
            }
            long length = n - last - 1;
            answer -= length * (length + 1) / 2;
        } else {
            LongList remainders = new LongArrayList();
            long current = 1;
            remainders.add(1);
            answer = 0;
            for (int i : a) {
                if (i % m == 0) {
                    answer += calculate(remainders, m, k);
                    remainders = new LongArrayList();
                    current = 1;
                    remainders.add(1);
                } else {
                    current *= i;
                    current %= m;
                    remainders.add(current);
                }
            }
            answer += calculate(remainders, m, k);
        }
        out.printLine(answer);
    }

    private long calculate(LongList remainders, int m, int k) {
        remainders.inPlaceReverse();
        Counter<Long> counter = new Counter<>();
        long answer = 0;
        for (long i : remainders) {
            answer += counter.get(i * k % m);
            counter.add(i);
        }
        return answer;
    }
}
