package on2015_05.on2015_05_30_Yandex_Algorithm_2015_Round_2.C___________________;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] stones = in.readString().toCharArray();
        int[] qty = new int[26];
        for (char c : stones) {
            qty[c - 'a']++;
        }
        int xor = 0;
        for (int i : qty) {
            xor ^= i;
        }
        int max = ArrayUtils.maxElement(qty);
        if (max == 1 && xor != 0 || max > 1 && xor == 0) {
            out.printLine(2);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if ((xor ^ qty[i]) <= qty[i] + 1) {
                int temp = qty[i];
                qty[i] = 0;
                int move;
                if (ArrayUtils.maxElement(qty) <= 1) {
                    if (ArrayUtils.sumArray(qty) % 2 == 1) {
                        move = temp;
                    } else {
                        move = temp - 1;
                    }
                } else {
                    move = temp - (xor ^ temp);
                }
                if (move <= 0) {
                    qty[i] = temp;
                    continue;
                }
                out.print(1);
                out.print(' ');
                for (int j = 0; j < move; j++) {
                    out.print((char)(i + 'a'));
                }
                out.printLine();
                return;
            }
        }
    }
}
