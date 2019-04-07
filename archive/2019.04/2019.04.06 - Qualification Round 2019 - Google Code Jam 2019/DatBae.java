package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DatBae {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.readInt();
        for (int test = 0; test < t; test++) {
            int n = in.readInt();
            int b = in.readInt();
            int f = in.readInt();
            char[][] query = new char[5][n];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < n; j++) {
                    query[i][j] = (char) ((j >> i & 1) + '0');
                }
            }
            char[][] results = new char[5][];
            for (int i = 0; i < 5; i++) {
                out.printLine(query[i]);
                out.flush();
                results[i] = in.readString().toCharArray();
            }
            IntList answer = new IntArrayList();
            int at = 0;
            for (int i = 0; i < n - b; i++) {
                boolean ok;
                do {
                    ok = true;
                    for (int j = 0; j < 5; j++) {
                        if (results[j][i] != query[j][at]) {
                            ok = false;
                        }
                    }
                    if (!ok) {
                        answer.add(at++);
                    }
                } while (!ok);
                at++;
            }
            while (at < n) {
                answer.add(at++);
            }
            out.printLine(answer);
            out.flush();
            if (in.readInt() != 1) {
                return;
            }
        }
    }
}
