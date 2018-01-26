package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.highestOneBit;

public class TransactionCertificates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int p = in.readInt();
        int m = in.readInt();
        while (n <= 1000) {
            n += n;
        }
        int x = highestOneBit(n);
        int[] a = new int[n];
        a[0] = 1;
        for (int i = 1; i < x; i *= 2) {
            for (int j = 0; j < i; j++) {
                a[i + j] = 3 - a[j];
            }
        }
        int[] b = new int[n];
        for (int i = 0; i < x; i++) {
            b[i] = 3 - a[i];
        }
        for (int i = x; i < n; i++) {
            a[i] = b[i] = 1;
        }
        out.printLine(a);
        out.printLine(b);
//        Map<Long, IntList> answer = new HashMap<>();
//        Random random = new Random(239);
//        while (true) {
//            IntList current = new IntArrayList(n);
//            long hash = 0;
//            for (int i = 0; i < n; i++) {
//                hash *= p;
//                int value = 1 + random.nextInt(2);
//                current.add(value);
//                hash += value;
//                hash &= m - 1;
//            }
//            if (answer.containsKey(hash)) {
//                out.printLine(answer.get(hash));
//                out.printLine(current);
//                return;
//            }
//            answer.put(hash, current);
//        }
    }
}
