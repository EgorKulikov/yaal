package on2016_03.on2016_03_13_GP_of_Tatarstan.H___Messenger;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.InputMismatchException;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readLine();
        int n = in.readInt();
        int shift = 0;
        boolean revert = false;
        for (int i = 0; i < n; i++) {
            int x;
            try {
                x = in.readInt();
            } catch (InputMismatchException e) {
                x = 0;
            }
            if (x < 0 || x > s.length()) {
                while (true);
            }
            if (revert) {
                x = s.length() - x;
            }
            shift += x;
            shift %= s.length();
            revert = !revert;
        }
        if (revert) {
            shift = s.length() - shift;
            s = StringUtils.reverse(s);
        }
        out.printLine(s.substring(shift) + s.substring(0, shift));
    }
}
