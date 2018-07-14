package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.max;

public class ESynchronizedSubsequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String s = in.readString();
        int[] a = new int[n];
        int[] b = new int[n];
        int atA = 0;
        int atB = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (s.charAt(i) == 'a') {
                a[atA++] = i;
            } else {
                b[atB++] = i;
            }
        }
        boolean canStartB = false;
        for (int i = 0; i < n; i++) {
            if (b[i] < a[i]) {
                canStartB = true;
            }
        }
        if (!canStartB) {
            int times = 0;
            int at = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] > at) {
                    times++;
                    at = b[i];
                }
            }
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < times; i++) {
                answer.append("ab");
            }
            out.printLine(answer);
            return;
        }
        String[] best = new String[n + 1];
        best[n] = "";
        for (int i = n - 1; i >= 0; i--) {
            best[i] = best[i + 1];
            if (a[i] < b[i]) {
                int j = n;
                while (a[j - 1] > b[i]) {
                    j--;
                }
                best[i] = max(best[i], "ab" + best[j]);
                continue;
            }
            int include = 1;
            int lastA = a[i];
            for (int j = i + 1; j < n; j++) {
                if (b[j] < lastA) {
                    include++;
                    lastA = a[j];
                }
            }
            StringBuilder varBuilder = new StringBuilder(2 * include);
            int aa = i;
            int bb = i;
            for (int j = 0; j < 2 * include; j++) {
                if (aa == i + include) {
                    varBuilder.append('b');
                    bb++;
                } else if (bb == i + include) {
                    varBuilder.append('a');
                    aa++;
                } else if (a[aa] < b[bb]) {
                    varBuilder.append('a');
                    aa++;
                } else {
                    varBuilder.append('b');
                    bb++;
                }
            }
            String variant = varBuilder.toString() + best[i + include];
            best[i] = max(best[i], variant);
        }
        out.printLine(best[0]);
    }
}
