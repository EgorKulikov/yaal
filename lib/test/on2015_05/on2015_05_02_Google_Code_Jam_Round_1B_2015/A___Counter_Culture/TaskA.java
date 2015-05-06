package on2015_05.on2015_05_02_Google_Code_Jam_Round_1B_2015.A___Counter_Culture;



import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            long count;
            long answer;

            @Override
            public void read(InputReader in) {
                count = in.readLong();
            }

            @Override
            public void solve() {
                long current = 1;
                answer = 1;
                int length = 1;
                while (current * 10 <= count) {
                    if (current == 1) {
                        current = 10;
                        answer += 9;
                        length++;
                        continue;
                    }
                    long moves = 0;
                    for (int i = 0; i < length / 2; i++) {
                        moves *= 10;
                        moves += 9;
                    }
                    long originalCurrent = current;
                    answer += moves;
                    current += moves;
                    answer++;
                    current = Long.parseLong(StringUtils.reverse(String.valueOf(current)));
                    answer += originalCurrent * 10 - current;
                    current = originalCurrent * 10;
                    length++;
                }
                if (count < 10) {
                    answer += count - current;
                    return;
                }
                if (current == count) {
                    return;
                }
                long step = Long.parseLong(String.valueOf(count).substring(0, length / 2));
                if (ArrayUtils.count(String.valueOf(count).substring(length / 2).toCharArray(), '0') == length - length / 2) {
                    step--;
                }
                if (String.valueOf(step).length() < length / 2) {
                    answer += count - current;
                    return;
                }
                step = Long.parseLong(StringUtils.reverse(String.valueOf(step)));
                answer += step;
                current += step;
                if (step != 1) {
                    answer++;
                    current = Long.parseLong(StringUtils.reverse(String.valueOf(current)));
                }
                answer += count - current;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
