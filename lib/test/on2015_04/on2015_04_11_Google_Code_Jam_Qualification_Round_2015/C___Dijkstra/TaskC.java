package on2015_04.on2015_04_11_Google_Code_Jam_Qualification_Round_2015.C___Dijkstra;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskC {
    static class Quaternion {
        int type;
        int sign;

        public Quaternion(int type, int sign) {
            this.type = type;
            this.sign = sign;
        }

        Quaternion multiply(Quaternion arg) {
            if (type == 0) {
                return new Quaternion(arg.type, sign * arg.sign);
            }
            if (arg.type == 0) {
                return new Quaternion(type, sign * arg.sign);
            }
            if (type == arg.type) {
                return new Quaternion(0, -sign * arg.sign);
            }
            if (arg.type == type + 1 || type == 3 && arg.type == 1) {
                return new Quaternion(6 - type - arg.type, sign * arg.sign);
            }
            return new Quaternion(6 - type - arg.type, -sign * arg.sign);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int length;
            char[] string;
            long times;
            boolean answer;

            @Override
            public void read(InputReader in) {
                length = in.readInt();
                times = in.readLong();
                string = IOUtils.readCharArray(in, length);
            }

            @Override
            public void solve() {
                Quaternion value = new Quaternion(0, 1);
                for (char c : string) {
                    value = value.multiply(new Quaternion(c - 'i' + 1, 1));
                }
                Quaternion total = new Quaternion(0, 1);
                for (int i = 0; i < (times & 3); i++) {
                    total = total.multiply(value);
                }
                if (total.type != 0 || total.sign != -1) {
                    answer = false;
                    return;
                }
                long firstI = Long.MAX_VALUE / 2;
                Quaternion current = new Quaternion(0, 1);
                for (int i = 0; i < length; i++) {
                    current = current.multiply(new Quaternion(string[i] - 'i' + 1, 1));
                    Quaternion shifted = current;
                    for (int j = 0; j < 4; j++) {
                        if (shifted.type == 1 && shifted.sign == 1) {
                            firstI = Math.min(firstI, i + j * length + 1);
                        }
                        shifted = value.multiply(shifted);
                    }
                }
                long lastK = Long.MAX_VALUE / 2;
                current = new Quaternion(0, 1);
                for (int i = length - 1; i >= 0; i--) {
                    current = new Quaternion(string[i] - 'i' + 1, 1).multiply(current);
                    Quaternion shifted = current;
                    for (int j = 0; j < 4; j++) {
                        if (shifted.type == 3 && shifted.sign == 1) {
                            lastK = Math.min(lastK, length - i + length * j);
                        }
                        shifted = shifted.multiply(value);
                    }
                }
                answer = firstI + lastK < times * length;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer ? "YES" : "NO");
            }
        }, 4);
    }
}
