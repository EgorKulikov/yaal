package on2012_12.on2012_12_26_Volume_5._1423___String_Tale;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1423 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        String first = readString(in, count);
        String second = readString(in, count);
        String sample = first + second + second;
        int[] z = StringUtils.zAlgorithm(sample);
        for (int i = count; i < 2 * count; i++) {
            if (z[i] >= count) {
                out.printLine(i - count);
                return;
            }
        }
        out.printLine(-1);
    }

    private String readString(InputReader in, int count) {
        char[] result = new char[count];
        for (int i = 0; i < count; i++) {
            int c;
            do {
                c = in.read();
                if (c < 0)
                    c += 256;
            } while (c <= 32);
            result[i] = (char) c;
        }
        return new String(result);
    }
}
