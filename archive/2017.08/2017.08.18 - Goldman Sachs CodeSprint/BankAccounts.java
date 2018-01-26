package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class BankAccounts {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int x = in.readInt();
        int d = in.readInt();
        int[] p = readIntArray(in, n);
        int flat = 0;
        int percentage = 0;
        for (int i : p) {
            if (i * x <= k * 100) {
                flat += k;
            } else {
                percentage += i;
            }
        }
        if ((d - flat) * 100 >= percentage * x) {
            out.printLine("fee");
        } else {
            out.printLine("upfront");
        }
    }
}
