package on2017_04.on2017_04_08_Google_Code_Jam_Qualification_Round_2017.C___Bathroom_Stalls;



import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.generated.collections.pair.LongLongPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

import static net.egork.misc.MiscUtils.min;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            long n;
            long k;

            @Override
            public void read(InputReader in) {
                n = in.readLong();
                k = in.readLong();
            }

            LongLongPair answer;
            Map<LongLongPair, LongLongPair> result = new HashMap<>();

            @Override
            public void solve() {
                answer = solve(n, k);
                result = null;
            }

            private LongLongPair solve(long n, long k) {
                LongLongPair key = new LongLongPair(n, k);
                if (result.containsKey(key)) {
                    return result.get(key);
                }
                if (k == 1) {
                    LongLongPair answer = new LongLongPair((n - 1) >> 1, n >> 1);
                    result.put(key, answer);
                    return answer;
                }
                long smallN = (n - 1) >> 1;
                long smallK = (k - 1) >> 1;
                long bigN = n >> 1;
                long bigK = k >> 1;
                LongLongPair answer;
                if (smallK == 0) {
                    answer = solve(bigN, bigK);
                } else {
                    answer = min(solve(bigN, bigK), solve(smallN, smallK));
                }
                result.put(key, answer);
                return answer;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer.second, answer.first);
            }
        }, 4);
    }
}
