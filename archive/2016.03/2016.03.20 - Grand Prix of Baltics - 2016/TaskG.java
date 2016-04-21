package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int p = in.readInt();
        int q = in.readInt();
        IntList list = new IntArrayList();
        while (p != 0) {
            list.add(q / p);
            int temp = q % p;
            q = p;
            p = temp;
        }
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i : list) {
            BigInteger temp = a.add(b.multiply(BigInteger.valueOf(i)));
            a = b;
            b = temp;
        }
        BigInteger g = a.gcd(b);
        a = a.divide(g);
        b = b.divide(g);
        out.printLine(a, b);
    }
}
